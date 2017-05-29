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
open class RoleService (val repository: RoleRepository){

    fun findAll() = repository.findAll()
    fun findById(id: Long) = repository.findOne(id)
    fun save(role: Role) = repository.save(role)
}