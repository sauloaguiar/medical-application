package com.learning.medicare.administration

import com.learning.medicare.prescription.Prescription
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by sauloaguiar on 7/13/17.
 */
@Entity
@Table(name = "Administrations")
class Administration (
        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        @field:NotNull var medicine: Prescription? = null,
        @field:NotBlank var timestamp: Long = 0,
        @field:NotBlank var patient_id: Long = 0,
        @field:NotBlank var caregiver_id: Long = 0,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)