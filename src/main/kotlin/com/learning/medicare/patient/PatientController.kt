package com.learning.medicare.patient

import org.springframework.web.bind.annotation.*

/**
 * Created by sauloaguiar on 3/21/17.
 */
@RestController
@RequestMapping("/patient")
class PatientController(val repository: PatientRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = repository.findOne(id)

    @PostMapping("/")
    fun savePatient(@RequestBody patient: Patient) = repository.save(patient)

}

