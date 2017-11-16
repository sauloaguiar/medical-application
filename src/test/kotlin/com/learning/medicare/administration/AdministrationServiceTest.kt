package com.learning.medicare.administration

import com.learning.medicare.prescription.Prescription
import com.learning.medicare.prescription.Timetable
import com.learning.medicare.user.Role
import com.learning.medicare.user.RoleType
import com.learning.medicare.user.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner
import java.sql.Timestamp
import java.util.*

/**
 * Created by sauloaguiar on 7/13/17.
 */
class AdministrationServiceTest {

    @InjectMocks
    lateinit var service: AdministrationService

    @Mock
    lateinit var repository: AdministrationRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldLoadAdministrationById() {
        val example = Administration(caregiverId = 0, patientId = 0)
        Mockito.`when`(repository.findOne(1)).thenReturn(example)

        assertThat(service.findOne(1)).isEqualTo(example)
    }

    @Test
    fun shouldLoadAllAdministrations() {
        val example1 = Administration(caregiverId = 0, patientId = 0)
        val example2 = Administration(caregiverId = 0, patientId = 0)

        Mockito.`when`(repository.findAll()).thenReturn(listOf(example1, example2))
        assertThat(service.findAll()).hasSize(2)
        assertThat(service.findAll()).hasSameElementsAs(listOf(example1, example2))
    }

    @Test
    fun shouldSaveAdministration() {
        val example = Administration(caregiverId = 0, patientId = 0)
        Mockito.`when`(repository.save(example)).thenReturn(example)
        service.save(example)
        Mockito.verify(repository).save(example)
    }

    @Test
    fun shouldLoadAdministrationList() {
        val patient1 = User("Saulo","Aguiar", Date(1989, 10, 26), id = 1)
        val caregiver = User("Nataly","Results", Date(1987, 2,25), roles = setOf(Role(RoleType.ADMIN)), id = 4)
        val prescription = Prescription(
                user = patient1,
                timetable = Timetable("once in a week", "* * 7* * &"),
                medicineName = "paracetamol",
                medicineDoseUnit = "mg",
                medicineDose = 20,
                startDate = Date(2017, 10, 1),
                endDate = Date(2017, 11, 1))
        val adm = Administration(prescription = prescription, patientId = patient1.id, caregiverId = caregiver.id)

        Mockito.`when`(repository.findAdministrationByPatientId(patient1.id)).thenReturn(listOf(adm))

        val sequence = service.getAdministrationsForUser(patient1.id)
        assertThat(sequence).isNotNull()
        assertThat(sequence.toList()).asList().hasSameElementsAs(listOf(adm))

        Mockito.verify(repository, Mockito.times(1)).findAdministrationByPatientId(1)
        Mockito.verifyNoMoreInteractions(repository)

    }
}

