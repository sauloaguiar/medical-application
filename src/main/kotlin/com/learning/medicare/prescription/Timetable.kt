package com.learning.medicare.prescription

import com.fasterxml.jackson.annotation.*
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.*

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class Timetable(
        @field:NotBlank var frequency: String = "",
        @field:NotBlank var cron: String = "",
        @OneToMany(mappedBy = "timetable", cascade = arrayOf(CascadeType.ALL))
        var prescriptions: List<Prescription> = emptyList(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0
)