package com.learning.medicare.user

import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@RunWith(SpringRunner::class)
@WebMvcTest(UserController::class)
class UserControllerTests {

	@Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var service: UserServiceContract

    @Test
 	fun shouldLoadUserById() {
		var user = User("Saulo","Aguiar", Date(1989, 10, 26))
		Mockito.`when`(service.findOne(1)).thenReturn(user)
		mockMvc.perform(get("/user/1"))
				.andExpect(MockMvcResultMatchers.status().isOk)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", `is`(user.firstName)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName", `is`(user.lastName)))
	}

}
