package com.learning.medicare.user

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * Created by sauloaguiar on 5/4/17.
 */
@Entity @Table(name = "roles")
class Role (val name: String = "",
            @ManyToMany(mappedBy = "roles")
            val users: Set<User> = emptySet(),
            @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Long = 0)