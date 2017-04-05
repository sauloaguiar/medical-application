package com.learning.medicare.patient;

import com.learning.medicare.prescription.PrescriptionRepository
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.ContentResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.hamcrest.core.Is.`is`
import java.util.*

class PatientControllerTests {

	lateinit var mockMvc: MockMvc

	@InjectMocks
	lateinit var controller: PatientController

	@Mock
	lateinit var repository: PatientRepository

	@Before
	fun setup() {
		MockitoAnnotations.initMocks(this)
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
	}

	@Test
 	fun shouldLoadUserById() {
		var user = Patient("Saulo","Aguiar", Date(1989, 10, 26))
		Mockito.`when`(repository.findOne(1)).thenReturn(user)
		mockMvc.perform(get("/patient/1"))
				.andExpect(MockMvcResultMatchers.status().isOk)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", `is`(user.firstName)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName", `is`(user.lastName)))
	}

}
