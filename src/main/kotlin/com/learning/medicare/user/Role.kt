package com.learning.medicare.user

import com.fasterxml.jackson.annotation.*
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by sauloaguiar on 5/4/17.
 */
@Entity
@Table(name = "roles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
class Role (
        var name: RoleType = RoleType.PATIENT,
        @JsonIgnore
        @ManyToMany(mappedBy = "roles", cascade = arrayOf(CascadeType.ALL))
        val users: Set<User> = emptySet(),
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Long = 0) {

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other?.javaClass != javaClass) return false

                other as Role

                if (name != other.name) return false
                if (users != other.users) return false
                if (id != other.id) return false

                return true
        }

        override fun hashCode(): Int {
                var result = name.hashCode()
                result = 31 * result + users.hashCode()
                result = 31 * result + id.hashCode()
                return result
        }
}

enum class RoleType {
        ADMIN, CAREGIVER, PATIENT
}