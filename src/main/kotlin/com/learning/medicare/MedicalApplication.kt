package com.learning.medicare

import com.learning.medicare.patient.Patient
import com.learning.medicare.patient.PatientRepository
import com.learning.medicare.prescription.Prescription
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
open class MedicalApplication {

    @Bean
    open fun init(repository: PatientRepository) = CommandLineRunner {
        repository.save(Patient("Saulo", "Aguiar", Date(1989, 10, 26), listOf(Prescription("Take beer twice a week"))))
        repository.save(Patient("Jonathan", "Freeman", Date(1987, 10, 26)))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(MedicalApplication::class.java, *args)

}

