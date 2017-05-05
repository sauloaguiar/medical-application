package com.learning.medicare.user

import org.springframework.stereotype.Service

/**
 * Created by sauloaguiar on 4/4/17.
 */
@Service
class UserService(val userRepository: UserRepository) {

    fun  findAll() = userRepository.findAll()
    fun  findOne(id: Long) = userRepository.findOne(id)
    fun  save(user: User) = userRepository.save(user)

}