package com.learning.medicare.user

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.*
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
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
				.andExpect(status().isOk)
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.firstName", `is`(user.firstName)))
				.andExpect(jsonPath("$.lastName", `is`(user.lastName)))
	}

    @Test
    fun shouldLoadAllUsers() {
        // given
        val patient1 = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val patient2 = User("Jonathan","Freeman", Date(1986, 10, 26), id = 2)
        val patient3 = User("Wonder","Woman", Date(1987, 10, 26), id = 3)

        // when
        Mockito.`when`(service.findAll()).thenReturn(listOf(patient1, patient2, patient3))

        // then
        mockMvc.perform(get("/user/"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize<Any>(3)))
                .andExpect(jsonPath("$[0].firstName", equalTo(patient1.firstName)))
                .andExpect(jsonPath("$[1].firstName", equalTo(patient2.firstName)))
                .andExpect(jsonPath("$[2].firstName", equalTo(patient3.firstName)))
    }

    @Test
    fun shouldCreateUser() {
        // given
        val testUser = User("Saulo","Aguiar", Date(1989, 10, 26))
        val createdUser = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1L, roles = emptySet())

        // when
        Mockito.`when`(service.save(testUser)).thenReturn(createdUser)

        // then
        mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(testUser)))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstName", `is`(createdUser.firstName)))
                .andExpect(jsonPath("$.lastName", `is`(createdUser.lastName)))
                .andExpect(jsonPath("$.id", `is`(1)))
    }

//    @Test
//    fun shouldRejectUserWithEmptyLastName() {
//        // given
//        val testUser = User("Saulo", "", Date(1989, 10, 26))
//
//        mockMvc.perform(post("/user/")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(ObjectMapper().writeValueAsBytes(testUser)))
//
//                .andExpect(status().isOk)
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//    }

	@Test
    fun shouldLoadPatientsForACaregiver(){
        val patient1 = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val patient2 = User("Jonathan","Freeman", Date(1986, 10, 26), id = 2)
        val patient3 = User("Wonder","Woman", Date(1987, 10, 26), id = 3)
        val caregiver = User("Nataly","Results", Date(1987, 2,25), roles = setOf(Role("admin")), id = 4)

        Mockito.`when`(service.getAllPatientsFor(caregiver.id)).thenReturn(listOf(patient1, patient2, patient3).asSequence())

        mockMvc.perform(get("/user/${caregiver.id}/patients"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize<Any>(3)))
                .andExpect(jsonPath("$[0].firstName", equalTo(patient1.firstName)))
                .andExpect(jsonPath("$[1].firstName", equalTo(patient2.firstName)))
                .andExpect(jsonPath("$[2].firstName", equalTo(patient3.firstName)))
    }

}
