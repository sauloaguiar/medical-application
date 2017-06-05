package com.learning.medicare.user

import org.springframework.web.bind.annotation.*

/**
 * Created by sauloaguiar on 3/21/17.
 */
@RestController
@RequestMapping("/role")
class RoleController(val roleService: RoleServiceContract) {

    @GetMapping("/")
    fun findAll() = roleService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = roleService.findById(id)

    @PostMapping("/")
    fun saveRole(@RequestBody role: Role) = roleService.save(role)
}


