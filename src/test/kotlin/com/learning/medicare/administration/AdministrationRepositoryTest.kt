package com.learning.medicare.administration

import com.learning.medicare.prescription.Prescription
import com.learning.medicare.prescription.Timetable
import com.learning.medicare.user.Role
import com.learning.medicare.user.RoleType
import com.learning.medicare.user.User
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
open class AdministrationRepositoryTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var administrationRepository: AdministrationRepository

    lateinit var patient: User
    lateinit var caregiver: User
    lateinit var prescription: Prescription

    @Before
    fun setup() {
        patient = User("Saulo","Aguiar", Date(1989, 10, 26))
        caregiver = User("Nataly","Results", Date(1987, 2,25), roles = setOf(Role(RoleType.ADMIN)))
        prescription = Prescription(
                user = patient,
                timetable = Timetable("once in a week", "* * 7* * &"),
                medicineName = "paracetamol",
                medicineDoseUnit = "mg",
                medicineDose = 20,
                startDate = Date(2017, 10, 1),
                endDate = Date(2017, 11, 1))


        entityManager.persist(patient)
        entityManager.persist(caregiver)
        entityManager.persist(prescription)

        entityManager.flush()
    }

    @Test
    fun shouldReturnAdministrationsByPatientId() {

        // given
        val adm = Administration(prescription = prescription, patientId = patient.id, caregiverId = caregiver.id)
        entityManager.persist(adm)
        entityManager.flush()

        // when
        val sequence = administrationRepository.findAdministrationByPatientId(patient.id)

        // then
        assertThat(sequence.toList()).hasSize(1)
        assertThat(sequence.toList()).hasSameElementsAs(listOf(adm))
    }

}