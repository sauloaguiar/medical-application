package com.learning.medicare.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.learning.medicare.administration.Administration
import com.learning.medicare.administration.AdministrationServiceContract
import com.learning.medicare.prescription.Prescription
import com.learning.medicare.prescription.PrescriptionServiceContract
import com.learning.medicare.prescription.Timetable
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
import org.junit.Assert.assertThat

import org.mockito.Mockito.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@RunWith(SpringRunner::class)
@WebMvcTest(UserController::class)
class UserControllerTests {

	@Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var userService: UserServiceContract

    @MockBean
    lateinit var prescriptionService: PrescriptionServiceContract

    @MockBean
    lateinit var administrationService: AdministrationServiceContract

    @Test
 	fun shouldLoadUserById() {
		var user = User("Saulo","Aguiar", Date(1989, 10, 26))
		Mockito.`when`(userService.findOne(1)).thenReturn(user)
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
        Mockito.`when`(userService.findAll()).thenReturn(listOf(patient1, patient2, patient3))

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
        val testUser = User("Saulo","Aguiar", birthday = Date(1989, 10, 26))
        val createdUser = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1L)

        // when
        Mockito.`when`(userService.save(testUser)).thenReturn(createdUser)

        // then
        mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(testUser)))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.user.firstName", `is`(createdUser.firstName)))
                .andExpect(jsonPath("$.user.lastName", `is`(createdUser.lastName)))
                .andExpect(jsonPath("$.user.id", `is`(1)))
    }

    @Test
    fun shouldRejectUserWithEmptyLastName() {
        // given
        val testUser = User("Saulo", "", Date(1989, 10, 26))

        // then
        mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(testUser)))

                .andExpect(status().is4xxClientError)
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

        // still unclear on what is returned when there's a validation error
    }

    @Test
    fun shouldRejectUserWithEmptyFirstName() {
        // given
        val testUser = User("", "Owell", Date(1989, 10, 26))

        // then
        mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(testUser)))

                .andExpect(status().is4xxClientError)
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

        // still unclear on what is returned when there's a validation error
    }

    @Test
    fun shouldLoadPatientPrescriptions() {
        // given
        val presc1 = Prescription(
                medicineName = "paracetamol",
                medicineDose = 500,
                medicineDoseUnit = "mg",
                endDate = Date(2017, 10, 26),
                startDate = Date(2017, 8, 26))
        val presc2 = Prescription(
                medicineName = "paracetamol",
                medicineDose = 500,
                medicineDoseUnit = "mg",
                endDate = Date(2016, 10, 30),
                startDate = Date(2016, 8, 26))

        val user = User("First", "Last", Date(1990, 10, 1), listOf(presc1, presc2), id = 1L)

        // when
        Mockito.`when`(userService.findOne(user.id)).thenReturn(user)

        // then
        mockMvc.perform(get("/user/${user.id}/prescriptions"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize<Any>(2)))
                .andExpect(jsonPath("$[0].medicineName", equalTo(presc1.medicineName)))
                .andExpect(jsonPath("$[1].medicineName", equalTo(presc2.medicineName)))

    }

	@Test
    fun shouldLoadPatientsForACaregiver() {
        val patient1 = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val patient2 = User("Jonathan","Freeman", Date(1986, 10, 26), id = 2)
        val patient3 = User("Wonder","Woman", Date(1987, 10, 26), id = 3)
        val caregiver = User("Nataly","Results", Date(1987, 2,25), roles = listOf(Role(RoleType.ADMIN)), id = 4)

        Mockito.`when`(userService.getAllPatientsFor(caregiver.id)).thenReturn(listOf(patient1, patient2, patient3).asSequence())

        mockMvc.perform(get("/user/${caregiver.id}/patients"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize<Any>(3)))
                .andExpect(jsonPath("$[0].firstName", equalTo(patient1.firstName)))
                .andExpect(jsonPath("$[1].firstName", equalTo(patient2.firstName)))
                .andExpect(jsonPath("$[2].firstName", equalTo(patient3.firstName)))
    }

    @Test
    fun shouldAssociatePatientToCaregiver() {
        val patient1 = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val caregiver = User("Nataly","Results", Date(1987, 2,25), roles = listOf(Role(RoleType.ADMIN)), id = 4)

        Mockito.`when`(userService.associate(patient1.id, caregiver.id)).thenReturn(TakesCareOf(patient1.id, caregiver.id, System.currentTimeMillis()))

        mockMvc.perform(post("/user/${caregiver.id}/patient")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(PatientDTO(patient1.id))))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.caregiverId", `is`(4)))
                .andExpect(jsonPath("$.patientId", `is`(1)))
                .andReturn()

        verify(userService, times(1)).associate(patient1.id, caregiver.id)
        verifyNoMoreInteractions(userService)
    }

    @Test
    fun shouldGet404ForPatientNotFoundWhenAssociating() {
        Mockito.`when`(userService.associate(1, 2)).thenThrow(UserNotFoundException(""))

        mockMvc.perform(post("/user/2/patient")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(PatientDTO(1))))
                .andExpect(status().isNotFound)
    }

    @Test
    fun shouldGet404ForCaregiverNotFoundWhenAssociating() {
        Mockito.`when`(userService.associate(1, 2)).thenThrow(UserNotFoundException(""))

        mockMvc.perform(post("/user/2/patient")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(PatientDTO(1))))
                .andExpect(status().isNotFound)
    }

    @Test
    fun shouldGet400ForPatientAndCaregiverWithSameIDWhenAssociating(){
        Mockito.`when`(userService.associate(1, 1)).thenThrow(InvalidAssociationException(""))

        mockMvc.perform(post("/user/1/patient")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(PatientDTO(1))))
                .andExpect(status().is4xxClientError)
    }

    @Test
    fun shouldGet400ForCaregiverWithoutRole() {
        val patient1 = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val fakeCaregiver = User("Nataly","Results", Date(1987, 2,25), id = 4)

        Mockito.`when`(userService.findOne(fakeCaregiver.id)).thenReturn(fakeCaregiver)
        Mockito.`when`(userService.associate(patient1.id, fakeCaregiver.id)).thenThrow(InvalidCaregiver(""))

        mockMvc.perform(post("/user/${fakeCaregiver.id}/patient")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsBytes(PatientDTO(patient1.id))))
                .andExpect(status().is4xxClientError)
    }

    @Test
    fun shouldGet400ForNonExistingUser() {
        Mockito.`when`(userService.findOne(1)).thenReturn(null)

        mockMvc.perform(get("/user/1/administrations"))
                .andExpect(status().is4xxClientError)

        verify(userService, times(1)).findOne(1)
        verifyNoMoreInteractions(userService)
    }

    @Test
    fun shouldGetListWithAdministrations() {
        val patient1 = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val caregiver = User("Nataly","Results", Date(1987, 2,25), roles = listOf(Role(RoleType.ADMIN)), id = 4)
        val prescription = Prescription(
                user = patient1,
                timetable = Timetable("once in a week", "* * 7* * &"),
                medicineName = "paracetamol",
                medicineDoseUnit = "mg",
                medicineDose = 20,
                startDate = Date(2017, 10, 1),
                endDate = Date(2017, 11, 1))
        val adm = Administration(prescription = prescription, patientId = patient1.id, caregiverId = caregiver.id)

        Mockito.`when`(userService.findOne(patient1.id)).thenReturn(patient1)
        Mockito.`when`(administrationService.getAdministrationsForUser(patientId = patient1.id)).thenReturn(sequenceOf(adm))

        mockMvc.perform(get("/user/${patient1.id}/administrations"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].caregiverId", `is`(caregiver.id.toInt())))
                .andExpect(jsonPath("$.[0].patientId", `is`(patient1.id.toInt())))
                .andExpect(jsonPath("$.[0].prescription.medicineDose", `is`(prescription.medicineDose)))
                .andExpect(jsonPath("$.[0].prescription.medicineDoseUnit", `is`(prescription.medicineDoseUnit)))
                .andExpect(jsonPath("$.[0].prescription.medicineName", `is`(prescription.medicineName)))

        verify(administrationService, times(1)).getAdministrationsForUser(1)
        verifyNoMoreInteractions(administrationService)
    }
}
