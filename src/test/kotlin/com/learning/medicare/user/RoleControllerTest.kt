package com.learning.medicare.user

import org.hamcrest.core.Is
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * Created by sauloaguiar on 5/28/17.
 */
@RunWith(SpringRunner::class)
@WebMvcTest(RoleController::class)
class RoleControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var service: RoleService

    @Test
    fun shouldReturnRole() {
        val role = Role("admin")
        print(service)
        given(service.findAll()).willReturn(listOf(role))

        mvc.perform(MockMvcRequestBuilders.get("/role/"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Is.`is`(role.name)))

    }
}