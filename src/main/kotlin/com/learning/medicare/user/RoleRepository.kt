package com.learning.medicare.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by sauloaguiar on 5/16/17.
 */
@Repository
interface RoleRepository : CrudRepository<Role, Long>