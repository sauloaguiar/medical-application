package com.learning.medicare.patient

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by sauloaguiar on 3/21/17.
 */
@Repository
interface PatientRepository : CrudRepository<Patient, Long>