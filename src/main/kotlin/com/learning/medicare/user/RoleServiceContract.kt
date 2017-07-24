package com.learning.medicare.user

import org.springframework.stereotype.Service

/**
 * Created by sauloaguiar on 5/28/17.
 */
@Service
open class RoleService(val repository: RoleRepository) : RoleServiceContract {
    override fun findAll(): Iterable<Role> = repository.findAll()
    override fun findById(id: Long): Role = repository.findOne(id)
    override fun save(role: Role): Role = repository.save(role)
}

interface RoleServiceContract {
    fun findAll(): Iterable<Role>
    fun findById(id: Long): Role
    fun save(role: Role): Role
}