package com.learning.medicare.user

import org.springframework.web.bind.annotation.*

/**
 * Created by sauloaguiar on 3/21/17.
 */
@RestController
@RequestMapping("/user")
class UserController(val userService: UserServiceContract) {

    @GetMapping("/")
    fun findAll() = userService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = userService.findOne(id)

    @PostMapping("/")
    fun savePatient(@RequestBody user: User) = userService.save(user)

    @GetMapping("/{id}/prescriptions")
    fun getAllPrescriptions(@PathVariable id: Long) = userService.findOne(id).prescriptions

}

