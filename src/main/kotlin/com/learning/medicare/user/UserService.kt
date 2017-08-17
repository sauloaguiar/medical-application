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

    override fun associate(patientId: Long, caregiverId: Long): TakesCareOf {
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

    fun associate(patientId: Long, caregiverId: Long): TakesCareOf
    fun getAllPatientsFor(caregiverId: Long): Sequence<User>
}
