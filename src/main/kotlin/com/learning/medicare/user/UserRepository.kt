package com.learning.medicare.user

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

/**
 * Created by sauloaguiar on 3/21/17.
 */
@Repository
interface UserRepository : CrudRepository<User, Long>, UserMatcher {

    @Query("select * from users u where u.id in (select t.patient_id from takes_care_of t where t.caregiver_id = ?1)", nativeQuery = true)
    fun getAllPatientsFor(caregiverId: Long): Iterable<User>
}

interface UserMatcher {
    fun associate(patientId: Long, caregiverId: Long): TakesCareOf
}

class UserRepositoryImpl(@PersistenceContext private val entityManager: EntityManager) : UserMatcher {
    override fun associate(patientId: Long, caregiverId: Long): TakesCareOf {
        val take = TakesCareOf(patientId, caregiverId, System.currentTimeMillis())
        entityManager.persist(take)
        entityManager.flush()
        return take
    }
}