package com.learning.medicare.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by sauloaguiar on 3/21/17.
 */
@Repository
interface UserRepository : CrudRepository<User, Long>