package com.learning.medicare.prescription

import com.learning.medicare.patient.Patient
import com.learning.medicare.patient.PatientController
import com.learning.medicare.patient.PatientRepository
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.hamcrest.core.Is.`is`

/**
 * Created by sauloaguiar on 4/4/17.
 */
class PrescriptionControllerTest {

    lateinit var mockMvc: MockMvc

    @InjectMocks
    lateinit var controller: PrescriptionController

    @Mock
    lateinit var repository: PrescriptionRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
    }

    @Test
    fun shouldLoadPrescriptionById() {
        var prescription = Prescription("Should take beer twice a week")
        Mockito.`when`(repository.findOne(1)).thenReturn(prescription)
        mockMvc.perform(MockMvcRequestBuilders.get("/prescription/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", `is`(prescription.description)))

    }

}