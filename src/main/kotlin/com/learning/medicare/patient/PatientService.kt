package com.learning.medicare.patient

import org.springframework.stereotype.Service

/**
 * Created by sauloaguiar on 4/4/17.
 */
@Service
class PatientService (val patientRepository: PatientRepository) {

    fun  findAll() = patientRepository.findAll()
    fun  findOne(id: Long) = patientRepository.findOne(id)
    fun  save(patient: Patient) = patientRepository.save(patient)

}