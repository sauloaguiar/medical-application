package com.learning.medicare.administration

import org.springframework.stereotype.Service

/**
 * Created by sauloaguiar on 7/13/17.
 */
@Service
class AdministrationService (val repository: AdministrationRepository) : AdministrationServiceContract {
    override fun findInRange(start: Long, end: Long): Iterable<Administration> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): Iterable<Administration> = repository.findAll()
    override fun save(administration: Administration): Administration = repository.save(administration)
    override fun findOne(id: Long): Administration = repository.findOne(id)
}

interface AdministrationServiceContract {
    fun findOne(id: Long): Administration
    fun findAll(): Iterable<Administration>
    fun save(administration: Administration): Administration
    fun findInRange(start: Long, end: Long): Iterable<Administration>
//    fun findBy
}