package com.learning.medicare.prescription

import org.springframework.stereotype.Service

/**
 * Created by sauloaguiar on 4/10/17.
 */
@Service
class PrescriptionService (val repository: PrescriptionRepository) : PrescriptionServiceContract {
    override fun findOne(id: Long) = repository.findOne(id)
    override fun findAll() = repository.findAll()
    override fun save(prescription: Prescription) = repository.save(prescription)
}

interface PrescriptionServiceContract {
    fun findAll(): Iterable<Prescription>
    fun findOne(id: Long): Prescription
    fun save(prescription: Prescription): Prescription
}