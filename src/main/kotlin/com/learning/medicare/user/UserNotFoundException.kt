package com.learning.medicare.user

class UserNotFoundException(override val message: String) : Exception(message)