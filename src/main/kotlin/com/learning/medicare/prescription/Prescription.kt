package com.learning.medicare.prescription

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.learning.medicare.user.User
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by sauloaguiar on 3/21/17.
 */
@Entity
@Table(name = "prescription")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
data class Prescription(
        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        @JsonBackReference(value = "user-reference")
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        @field:NotNull var user: User? = null,

        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "timetable_id", referencedColumnName = "id")
        @field:NotNull val timetable: Timetable? = null,

        var assignedAt: Long = System.currentTimeMillis(),
        var startDate: Long = 0,
        var endDate: Long = 0,
        @field:NotBlank var medicineName: String = "",
        var medicineDose: Int = 0,
        @field:NotBlank var medicineDoseUnit: String = "",
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)