package com.learning.medicare.user

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
open class UserRepositoryTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var userRepository: UserRepository

    lateinit var patient: User
    lateinit var caregiver: User

    @Before
    fun setUp() {
        patient = User("Saulo","Aguiar", Date(1989, 10, 26))
        caregiver = User("Nataly","Results", Date(1987, 2,25), roles = setOf(Role("admin")))

        entityManager.persist(patient)
        entityManager.persist(caregiver)
        entityManager.flush()

    }

    @Test
    fun shouldAssociatePatientToCaregiver() {
        // when
        val careOf = userRepository.associate(patient.id, caregiver.id)

        // then
        assertThat(careOf.caregiverId).isEqualTo(caregiver.id)
        assertThat(careOf.patientId).isEqualTo(patient.id)
    }


    @Test
    fun shouldRetrieveDependentsFromCaregiver(){
        // given
        userRepository.associate(patient.id, caregiver.id)
        entityManager.flush()

        // when
        val sequence = userRepository.getAllPatientsFor(caregiver.id)

        // then
        assertThat(sequence.toList()).hasSize(1)
        assertThat(sequence.toList()).hasSameElementsAs(listOf(patient))
    }
}