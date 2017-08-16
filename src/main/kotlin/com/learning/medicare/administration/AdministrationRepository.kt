package com.learning.medicare.administration

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by sauloaguiar on 7/13/17.
 */
interface AdministrationRepository : CrudRepository<Administration, Long> {
//    fun findByTimestampGreaterThanEqualAndLessThanEqual(startInterval: Long, endInterval: Long)
}