package com.learning.medicare.prescription

/**
 * Created by sauloaguiar on 4/10/17.
 */
class PrescriptionService (val repository: PrescriptionRepository){
    fun findOne(id: Long) = repository.findOne(id)
    fun findAll() = repository.findAll()
    fun save(prescription: Prescription) = repository.save(prescription)
}