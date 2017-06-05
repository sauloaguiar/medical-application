package com.learning.medicare.user

import org.springframework.stereotype.Service

/**
 * Created by sauloaguiar on 4/4/17.
 */
@Service
class UserService(val userRepository: UserRepository) : UserServiceContract {
    override fun findAll() = userRepository.findAll()
    override fun findOne(id: Long) = userRepository.findOne(id)
    override fun save(user: User) = userRepository.save(user)
}

interface UserServiceContract {
    fun findAll(): Iterable<User>
    fun findOne(id: Long): User
    fun save(user: User): User
}
