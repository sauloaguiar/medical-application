package com.learning.medicare.user

import javax.persistence.*

@Entity
@Table
data class TakesCareOf(
        var patientId: Long = 0,
        var caregiverId: Long = 0,
        var assignedAt: Long = 0,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)