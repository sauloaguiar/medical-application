package com.learning.medicare.prescription

import com.learning.medicare.user.UserController
import com.learning.medicare.user.UserServiceContract
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
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

/**
 * Created by sauloaguiar on 4/4/17.
 */
@RunWith(SpringRunner::class)
@WebMvcTest(PrescriptionController::class)
class PrescriptionControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var service: PrescriptionServiceContract

    @Test
    fun shouldLoadPrescriptionById() {
        var prescription = Prescription(
                medicineName = "paracetamol",
                medicineDose = 500,
                medicineDoseUnit = "mg",
                endDate = Date(2017, 10, 26).time,
                startDate = Date(2017, 8, 26).time)

        Mockito.`when`(service.findOne(1)).thenReturn(prescription)
        mockMvc.perform(MockMvcRequestBuilders.get("/prescription/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medicineName", `is`(prescription.medicineName)))

    }

}