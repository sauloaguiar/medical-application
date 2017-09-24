package com.learning.medicare.user

import com.github.kittinunf.result.Result
import com.learning.medicare.administration.Administration
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * Created by sauloaguiar on 4/4/17.
 */
@Service
@Transactional
open class UserService(private val userRepository: UserRepository) : UserServiceContract {

    override fun getAllPatientsFor(caregiverId: Long): Sequence<User> {
        return userRepository.getAllPatientsFor(caregiverId).asSequence()
    }

    override fun associate(patientId: Long, caregiverId: Long): Result<TakesCareOf, Exception> {
        val patient = userRepository.findOne(patientId)
        val caregiver = userRepository.findOne(caregiverId)
        if (patient == null || caregiver == null) {
            return Result.of { throw UserNotFoundException("No user found with provided Id") }
        }
        if (patientId == caregiverId) {
            return Result.of { throw InvalidAssociationException("Can't associate a patient to a caregiver with same id") }
        }
        if (!caregiver.roles.contains(Role(RoleType.CAREGIVER))) {
            return Result.of { throw InvalidCaregiver("Provided caregiver id doesn't have permissions") }
        }
        return Result.of { userRepository.associate(patientId, caregiverId) }
    }

    override fun findAll(): Iterable<User> = userRepository.findAll()
    override fun findOne(id: Long): User = userRepository.findOne(id)
    override fun save(user: User): User = userRepository.save(user)
}

interface UserServiceContract {
    fun findAll(): Iterable<User>
    fun findOne(id: Long): User
    fun save(user: User): User

    fun associate(patientId: Long, caregiverId: Long): Result<TakesCareOf, Exception>
    fun getAllPatientsFor(caregiverId: Long): Sequence<User>
}
