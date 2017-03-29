package com.learning.medicare.prescription

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by sauloaguiar on 3/21/17.
 */

@RestController
@RequestMapping("/prescription")
class PrescriptionController(val repository: PrescriptionRepository) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = repository.findOne(id)
}