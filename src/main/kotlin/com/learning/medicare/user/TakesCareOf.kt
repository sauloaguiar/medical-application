package com.learning.medicare.user

import javax.persistence.*

@Entity
@Table
data class TakesCareOf(
        val patientId: Long,
        val caregiverId: Long,
        val assignedAt: Long,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)