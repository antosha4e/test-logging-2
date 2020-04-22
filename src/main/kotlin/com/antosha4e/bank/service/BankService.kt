package com.antosha4e.bank.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BankService {

    private val log: Logger = LoggerFactory.getLogger(BankService::class.java)

    fun doTransaction() {

        log.info("Test card 1 4012888888881881")
        log.info("Test card 2 {}", 4012888888881882)

        log.info("Test phone 1 {}", "+49 312313311")
        log.info("Test phone 1 {}", "+64 27 111 2222")
    }
}
