package com.learning.medicare.user

import javax.persistence.*

/**
 * Created by sauloaguiar on 5/4/17.
 */
@Entity @Table(name = "role")
class Role (val name: String = "",
            @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Long = 0)