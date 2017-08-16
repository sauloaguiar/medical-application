package com.learning.medicare.user

import com.fasterxml.jackson.annotation.*
import com.learning.medicare.prescription.Prescription
import org.hibernate.validator.constraints.NotBlank
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by sauloaguiar on 3/21/17.
 */

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
data class User(
        @field:NotBlank var firstName: String = "",
        @field:NotBlank var lastName: String = "",
        @field:NotNull var birthday: Date = Date(),
        @JsonManagedReference(value = "user-reference")
        @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
        val prescriptions: List<Prescription> = emptyList(),

        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinTable(name = "user_role",
                joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")))
        val roles: Set<Role> = emptySet(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)