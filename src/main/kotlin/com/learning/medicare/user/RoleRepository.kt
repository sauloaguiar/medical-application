package com.learning.medicare.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by sauloaguiar on 5/16/17.
 */
@Repository
@Transactional
open interface RoleRepository : JpaRepository<Role, Long>