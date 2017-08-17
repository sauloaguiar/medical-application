package com.learning.medicare.user

import com.learning.medicare.prescription.Prescription
import com.learning.medicare.prescription.PrescriptionServiceContract
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Created by sauloaguiar on 3/21/17.
 */
@RestController
@RequestMapping("/user")
class UserController(val userService: UserServiceContract, val prescriptionService: PrescriptionServiceContract) {

    @GetMapping("/")
    fun findAll() = userService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = userService.findOne(id)

    @PostMapping("/")
    fun saveUser(@Valid @RequestBody user: User): ResponseEntity<HashMap<String, User>> =
        ResponseEntity.ok(hashMapOf(Pair("user", userService.save(user))))

    @GetMapping("/{id}/prescriptions")
    fun getAllPrescriptions(@PathVariable id: Long) = userService.findOne(id).prescriptions

    @GetMapping("/{caregiverId}/patients")
    fun getAllPatients(@PathVariable caregiverId: Long) = userService.getAllPatientsFor(caregiverId).toList()
//        userService.findOne(caregiverId).getAllPatients()

    @PostMapping("/{patientId}/prescription")
    fun addPrescriptionToPatient(@PathVariable patientId: Long, @RequestBody prescription: Prescription): Prescription {
        val user = userService.findOne(patientId)
        prescription.user = user
        return prescriptionService.save(prescription)
    }

    @PostMapping("/{caregiverId}/patient")
    fun associate(@PathVariable caregiverId: Long, @RequestBody payload: PatientDTO): TakesCareOf {
//        val obj = Parser().parse(StringBuilder(payload.textValue())) as JsonObject
        return userService.associate(payload.patient_id, caregiverId)
    }
}

