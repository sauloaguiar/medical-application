package com.learning.medicare.prescription

import com.learning.medicare.user.UserRepository
import com.learning.medicare.user.UserService
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
class PrescriptionServiceTest {

    @InjectMocks
    lateinit var service: PrescriptionService

    @Mock
    lateinit var repository: PrescriptionRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldLoadPrescriptionById() {
        var presc = Prescription(
                medicineName = "paracetamol",
                medicineDose = 500,
                medicineDoseUnit = "mg",
                endDate = Date(2017, 10, 26).time,
                startDate = Date(2017, 8, 26).time)
        Mockito.`when`(repository.findOne(1)).thenReturn(presc)

        assertThat(service.findOne(1)).isEqualTo(presc)
        assertThat(service.findOne(1).medicineName).isEqualTo(presc.medicineName)
    }

    @Test
    fun shouldReturnAllPrescription() {
        var presc1 = Prescription(
                medicineName = "paracetamol",
                medicineDose = 500,
                medicineDoseUnit = "mg",
                endDate = Date(2017, 10, 26).time,
                startDate = Date(2017, 8, 26).time)
        var presc2 = Prescription(
                medicineName = "Ibuprofen",
                medicineDose = 200,
                medicineDoseUnit = "mg",
                endDate = Date(2017, 10, 26).time,
                startDate = Date(2017, 8, 26).time)

        Mockito.`when`(repository.findAll()).thenReturn(listOf(presc1, presc2))
        assertThat(service.findAll()).hasSize(2)
        assertThat(service.findAll()).hasSameElementsAs(listOf(presc1, presc2))
    }

    @Test
    fun shouldSavePrescription() {
        var presc1 = Prescription(
                medicineName = "Ibuprofen",
                medicineDose = 200,
                medicineDoseUnit = "mg",
                endDate = Date(2017, 10, 26).time,
                startDate = Date(2017, 8, 26).time)
        Mockito.`when`(repository.save(presc1)).thenReturn(presc1)
        service.save(presc1)
        Mockito.verify(repository).save(presc1)
    }
}