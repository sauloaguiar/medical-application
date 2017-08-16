package com.learning.medicare.administration

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
        val example = Administration(timestamp = System.currentTimeMillis(), caregiver_id = 0, patient_id = 0)
        Mockito.`when`(repository.findOne(1)).thenReturn(example)

        assertThat(service.findOne(1)).isEqualTo(example)
    }

    @Test
    fun shouldLoadAllAdministrations() {
        val example1 = Administration(timestamp = System.currentTimeMillis(), caregiver_id = 0, patient_id = 0)
        val example2 = Administration(timestamp = System.currentTimeMillis(), caregiver_id = 0, patient_id = 0)

        Mockito.`when`(repository.findAll()).thenReturn(listOf(example1, example2))
        assertThat(service.findAll()).hasSize(2)
        assertThat(service.findAll()).hasSameElementsAs(listOf(example1, example2))
    }

    @Test
    fun shouldSaveAdministration() {
        val example = Administration(timestamp = System.currentTimeMillis(), caregiver_id = 0, patient_id = 0)
        Mockito.`when`(repository.save(example)).thenReturn(example)
        service.save(example)
        Mockito.verify(repository).save(example)
    }

    @Test
    fun shouldLoadAdministrationsWithinAnInterval() {

    }

    /*
    shouldNotCreateAdministrationWithout
        Patient
        Caregiver
        Prescription
     */
}

