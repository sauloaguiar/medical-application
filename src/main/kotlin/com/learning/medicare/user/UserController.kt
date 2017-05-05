package com.learning.medicare.user

import com.learning.medicare.prescription.PrescriptionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by sauloaguiar on 3/21/17.
 */
@RestController
@RequestMapping("/user")
class UserController(val userRepository: UserRepository) {

    @GetMapping("/")
    fun findAll() = userRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = userRepository.findOne(id)

    @PostMapping("/")
    fun savePatient(@RequestBody user: User) = userRepository.save(user)

    @GetMapping("/{id}/prescriptions")
    fun getAllPrescriptions(@PathVariable id: Long) = userRepository.findOne(id).prescriptions

}

