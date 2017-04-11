package com.learning.medicare.patient

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
    lateinit var service: PatientService

    @Mock
    lateinit var repository: PatientRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldLoadPatientById() {
        var user = Patient("Saulo","Aguiar", Date(1989, 10, 26))
        Mockito.`when`(repository.findOne(1)).thenReturn(user)

        assertThat(service.findOne(1)).isEqualTo(user)
        assertThat(service.findOne(1).firstName).isEqualTo(user.firstName)
        assertThat(service.findOne(1).lastName).isEqualTo(user.lastName)
    }

    @Test
    fun shouldLoadAllPatients() {
        var user1 = Patient("Saulo","Aguiar", Date(1989, 10, 26))
        var user2 = Patient("Jonathan","Freeman", Date(1989, 10, 26))

        Mockito.`when`(repository.findAll()).thenReturn(listOf(user1, user2))

        assertThat(service.findAll()).isNotNull
        assertThat(service.findAll()).hasSize(2)
        assertThat(service.findAll()).hasSameElementsAs(listOf(user1, user2))
    }

    @Test
    fun shouldSavePatient() {
        var user1 = Patient("Saulo","Aguiar", Date(1989, 10, 26))
        service.save(user1)
        Mockito.verify(repository).save(user1)
    }
}