package com.learning.medicare.user

class UserNotFoundException(override val message: String) : Exception(message)
class InvalidAssociationException(override val message: String) : Exception(message)
class InvalidCaregiver(override val message: String) : Exception(message)