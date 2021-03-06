package com.learning.medicare.user

import com.learning.medicare.administration.Administration
import com.learning.medicare.administration.AdministrationService
import com.learning.medicare.administration.AdministrationServiceContract
import com.learning.medicare.prescription.Prescription
import com.learning.medicare.prescription.PrescriptionServiceContract
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Created by sauloaguiar on 3/21/17.
 */
@RestController
@RequestMapping("/user")
class UserController(
        val userService: UserServiceContract,
        val prescriptionService: PrescriptionServiceContract,
        val administrationService: AdministrationServiceContract) {

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

    @PostMapping("/{patientId}/prescription")
    fun addPrescriptionToPatient(@PathVariable patientId: Long, @RequestBody prescription: Prescription): Prescription {
        val user = userService.findOne(patientId)
        prescription.user = user
        return prescriptionService.save(prescription)
    }

    @PostMapping("/{caregiverId}/patient")
    fun associate(@PathVariable caregiverId: Long, @RequestBody payload: PatientDTO): ResponseEntity<TakesCareOf> {
        return try {
            ResponseEntity(userService.associate(payload.patient_id, caregiverId), HttpStatus.OK)
        } catch (e: UserNotFoundException) {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        } catch (e: InvalidAssociationException) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        } catch (e: InvalidCaregiver) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{patientId}/administrations")
    fun getAdministrationsFromPatient(@PathVariable patientId: Long): ResponseEntity<List<Administration>> {
        if (userService.findOne(patientId) == null) {
            return ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(administrationService.getAdministrationsForUser(patientId).toList(), HttpStatus.OK)
    }
}

