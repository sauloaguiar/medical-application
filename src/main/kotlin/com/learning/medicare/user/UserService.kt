package com.learning.medicare.user

import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * Created by sauloaguiar on 4/4/17.
 */
@Service
@Transactional
open class UserService(val userRepository: UserRepository) : UserServiceContract {

    override fun getAllPatientsFor(caregiverId: Long): Sequence<User> {

//        return takesCareOfRepository.isResponsibleFor(findOne(caregiverId))
//              extra fields for every call

//        return userRepository.findOne(caregiverId).getAllPatients()
        return userRepository.getAllPatientsFor(caregiverId).asSequence()
    }

    @Throws(UserNotFoundException::class, InvalidAssociationException::class, InvalidCaregiver::class)
    override fun associate(patientId: Long, caregiverId: Long): TakesCareOf {
        val patient = userRepository.findOne(patientId)
        val caregiver = userRepository.findOne(caregiverId)
        if (patient == null || caregiver == null) {
            throw UserNotFoundException("No user found with provided Id")
        }
        if (patientId == caregiverId) {
            throw InvalidAssociationException("Can't associate a patient to a caregiver with same id")
        }
        if (!caregiver.roles.contains(Role(RoleType.CAREGIVER))) {
            throw InvalidCaregiver("Provided caregiver id doesn't have permissions")
        }
        return userRepository.associate(patientId, caregiverId)
    }

    override fun findAll(): Iterable<User> = userRepository.findAll()
    override fun findOne(id: Long): User = userRepository.findOne(id)
    override fun save(user: User): User = userRepository.save(user)
}

interface UserServiceContract {
    fun findAll(): Iterable<User>
    fun findOne(id: Long): User
    fun save(user: User): User

    @Throws(UserNotFoundException::class, InvalidAssociationException::class, InvalidCaregiver::class)
    fun associate(patientId: Long, caregiverId: Long): TakesCareOf
    fun getAllPatientsFor(caregiverId: Long): Sequence<User>
}
