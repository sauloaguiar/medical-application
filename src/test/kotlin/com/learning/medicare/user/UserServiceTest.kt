package com.learning.medicare.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

/**
 * Created by sauloaguiar on 4/10/17.
 */
class UserServiceTest {

    @InjectMocks
    lateinit var service: UserService

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldLoadPatientById() {
        var user = User("Saulo","Aguiar", Date(1989, 10, 26))
        Mockito.`when`(userRepository.findOne(1)).thenReturn(user)

        assertThat(service.findOne(1)).isEqualTo(user)
        assertThat(service.findOne(1).firstName).isEqualTo(user.firstName)
        assertThat(service.findOne(1).lastName).isEqualTo(user.lastName)
    }

    @Test
    fun shouldLoadAllPatients() {
        var user1 = User("Saulo","Aguiar", Date(1989, 10, 26))
        var user2 = User("Jonathan","Freeman", Date(1989, 10, 26))

        Mockito.`when`(userRepository.findAll()).thenReturn(listOf(user1, user2))

        assertThat(service.findAll()).isNotNull
        assertThat(service.findAll()).hasSize(2)
        assertThat(service.findAll()).hasSameElementsAs(listOf(user1, user2))
    }

    @Test
    fun shouldSavePatient() {
        var user1 = User("Saulo","Aguiar", Date(1989, 10, 26))
        Mockito.`when`(userRepository.save(user1)).thenReturn(user1)
        service.save(user1)
        Mockito.verify(userRepository).save(user1)
    }

    @Test
    fun shouldAssociatePatientToCaregiver() {
        val patient = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val caregiver = User("Nataly","Results", Date(1987, 2,25), roles = setOf(Role("admin")), id = 2)

        Mockito.`when`(userRepository.findOne(1)).thenReturn(patient)
        Mockito.`when`(userRepository.findOne(2)).thenReturn(caregiver)
        val time = System.currentTimeMillis()
        Mockito.`when`(userRepository.associate(patient.id, caregiver.id)).thenReturn(TakesCareOf(patient.id, caregiver.id, time))

        val careOf = service.associate(patient.id, caregiver.id)
        assertThat(careOf.caregiverId).isEqualTo(caregiver.id)
        assertThat(careOf.patientId).isEqualTo(patient.id)
    }

    @Test
    fun shouldLoadPatientsAssociatedToCaregiver() {
        val patient1 = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val patient2 = User("Jonathan","Freeman", Date(1986, 10, 26), id = 2)
        val patient3 = User("Wonder","Woman", Date(1987, 10, 26), id = 3)
        val caregiver = User("Nataly","Results", Date(1987, 2,25), roles = setOf(Role("admin")), id = 4)

        Mockito.`when`(userRepository.findOne(1)).thenReturn(patient1)
        Mockito.`when`(userRepository.findOne(2)).thenReturn(patient2)
        Mockito.`when`(userRepository.findOne(3)).thenReturn(patient3)
        Mockito.`when`(userRepository.findOne(4)).thenReturn(caregiver)

        Mockito.`when`(userRepository.getAllPatientsFor(caregiver.id)).thenReturn(listOf(patient1, patient2, patient3))

        val sequence = service.getAllPatientsFor(caregiver.id)
        assertThat(sequence).isNotNull()
        assertThat(sequence.toList()).asList().hasSameElementsAs(listOf(patient1, patient2, patient3))
    }
}