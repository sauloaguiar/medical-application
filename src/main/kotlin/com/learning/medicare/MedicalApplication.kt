package com.learning.medicare

import com.learning.medicare.user.User
import com.learning.medicare.user.UserRepository
import com.learning.medicare.prescription.Prescription
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
open class MedicalApplication

fun main(args: Array<String>) {
    SpringApplication.run(MedicalApplication::class.java, *args)
}

