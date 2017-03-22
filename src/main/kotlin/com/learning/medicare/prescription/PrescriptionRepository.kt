package com.learning.medicare.prescription

import org.springframework.data.repository.CrudRepository

/**
 * Created by sauloaguiar on 3/21/17.
 */
interface PrescriptionRepository : CrudRepository<Prescription, Long>