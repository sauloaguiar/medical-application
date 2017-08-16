package com.learning.medicare.user

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.*

/**
 * Created by sauloaguiar on 5/4/17.
 */
@Entity
@Table(name = "roles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
class Role (
        @field:NotBlank var name: String = "",
        @JsonIgnore
        @ManyToMany(mappedBy = "roles", cascade = arrayOf(CascadeType.ALL))
        val users: Set<User> = emptySet(),
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Long = 0)