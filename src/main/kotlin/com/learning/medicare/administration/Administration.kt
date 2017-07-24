package com.learning.medicare.administration

import com.learning.medicare.prescription.Prescription
import com.learning.medicare.user.User
import java.sql.Timestamp
import javax.persistence.*

/**
 * Created by sauloaguiar on 7/13/17.
 */
@Entity
@Table(name = "Administrations")
class Administration (
        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        val patient: User? = null,
        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        val caregiver: User? = null,
        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        val prescription: Prescription? = null,
        val timestamp: Timestamp,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)