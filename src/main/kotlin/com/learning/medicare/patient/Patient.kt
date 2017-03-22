package com.learning.medicare.patient

import com.learning.medicare.prescription.Prescription
import java.util.*
import javax.persistence.*

/**
 * Created by sauloaguiar on 3/21/17.
 */

@Entity @Table(name = "patients")
class Patient(
        val firstName: String = "",
        val lastName: String = "",
        val birthday: Date = Date(),
        @OneToMany(mappedBy = "patient", cascade = arrayOf(CascadeType.ALL))
        val prescriptions: List<Prescription> = emptyList(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0
        )