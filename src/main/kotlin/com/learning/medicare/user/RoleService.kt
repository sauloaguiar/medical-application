package com.learning.medicare.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import javax.transaction.Transactional

/**
 * Created by sauloaguiar on 5/28/17.
 */
@Service
open class RoleServiceImpl (val repository: RoleRepository) : RoleService {

    override fun findAll() = repository.findAll()
    override fun findById(id: Long) = repository.findOne(id)
    override fun save(role: Role) = repository.save(role)
}

interface RoleService {
    fun findAll(): List<Role>
    fun findById(id: Long): Role
    fun save(role: Role): Role
}