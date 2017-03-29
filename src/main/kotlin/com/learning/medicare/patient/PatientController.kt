package com.learning.medicare.patient

import com.learning.medicare.prescription.PrescriptionRepository
import org.springframework.web.bind.annotation.*

/**
 * Created by sauloaguiar on 3/21/17.
 */
@RestController
@RequestMapping("/patientRepository")
class PatientController(val patientRepository: PatientRepository, val prescriptionRepository: PrescriptionRepository) {

    @GetMapping("/")
    fun findAll() = patientRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = patientRepository.findOne(id)

    @PostMapping("/")
    fun savePatient(@RequestBody patient: Patient) = patientRepository.save(patient)

    @GetMapping("/{id}/prescriptions")
    fun getAllPrescriptions(@PathVariable id: Long) = patientRepository.findOne(id).prescriptions

}

