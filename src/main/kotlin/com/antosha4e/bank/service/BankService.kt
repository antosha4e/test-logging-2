package com.antosha4e.bank.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BankService {

    private val log: Logger = LoggerFactory.getLogger(BankService::class.java)

    fun doTransaction() {

        log.info("Started transaction")

        log.info("Test card 1 4012888888881881")
        log.info("Test card 2 {}", 4012888888881882)

        log.info("Finished transaction")
    }
}
