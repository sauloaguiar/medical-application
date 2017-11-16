package com.learning.medicare.administration

import com.learning.medicare.prescription.Prescription
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.validator.constraints.NotBlank
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by sauloaguiar on 7/13/17.
 */
@Entity
@Table(name = "Administrations")
class Administration (
        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        @field:NotNull var prescription: Prescription? = null,
        @CreationTimestamp
        var timestamp: Date = Date(),
        var patientId: Long = 0,
        var caregiverId: Long = 0,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)