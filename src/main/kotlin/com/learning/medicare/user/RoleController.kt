package com.learning.medicare.user

import com.learning.medicare.prescription.PrescriptionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by sauloaguiar on 3/21/17.
 */
@RestController
@RequestMapping("/role")
class RoleController(val roleService: RoleService) {

    @GetMapping("/")
    fun findAll() = roleService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = roleService.findById(id)

    @PostMapping("/")
    fun saveRole(@RequestBody role: Role) = roleService.save(role)
}


