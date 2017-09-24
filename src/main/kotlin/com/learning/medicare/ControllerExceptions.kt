package com.learning.medicare

import com.learning.medicare.user.InvalidCaregiver
import com.learning.medicare.user.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerExceptions {

    @ExceptionHandler(value = Exception::class)
    fun handleExceptionHandler(exception: RuntimeException) = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.localizedMessage)

    @ExceptionHandler(value = UserNotFoundException::class)
    fun handleUserNotFoundException(exception: RuntimeException, request: WebRequest): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.localizedMessage)
    }

    @ExceptionHandler(value = InvalidCaregiver::class)
    fun handleInvalidCaregiverException(exception: RuntimeException, request: WebRequest): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }
}