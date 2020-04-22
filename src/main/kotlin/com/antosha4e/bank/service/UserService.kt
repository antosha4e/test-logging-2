package com.antosha4e.bank.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UserService {

    private val log: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun doUserOperation() {

        log.info("Test phone 1 {}", "+49 312313311")
        log.info("Test phone 1 {}", "+64 27 111 2222")
    }
}
