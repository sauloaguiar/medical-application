package com.learning.medicare.user

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.learning.medicare.administration.Administration
import com.learning.medicare.prescription.Prescription
import java.util.*
import javax.persistence.*

/**
 * Created by sauloaguiar on 3/21/17.
 */

@Entity @Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
class User(
        val firstName: String = "",
        val lastName: String = "",
        val birthday: Date = Date(),
        @JsonManagedReference
        @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
        val prescriptions: List<Prescription> = emptyList(),
        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinTable(name = "users_roles",
                joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")))
        val roles: Set<Role> = emptySet(),

        @OneToMany(mappedBy = "patient")
        val takenAdministration: List<Administration> = emptyList(),

        @OneToMany(mappedBy = "caregiver")
        val givenAdministration: List<Administration> = emptyList(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0
        )