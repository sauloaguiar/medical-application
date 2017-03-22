package com.learning.medicare.prescription

import com.learning.medicare.patient.Patient
import javax.persistence.*

/**
 * Created by sauloaguiar on 3/21/17.
 */
@Entity
@Table(name = "prescriptions")
class Prescription(
        val description: String = "",
        @ManyToOne
        @JoinColumn(name = "patient_id", referencedColumnName = "id")
        val patient: Patient? = null,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)