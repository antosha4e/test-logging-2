package com.antosha4e.bank.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BankService {

    private val log: Logger = LoggerFactory.getLogger(BankService::class.java)

    fun doTransaction(cardNumber: String) {

        log.info("Started transaction")

        log.info("Test card 2 {}", cardNumber)

        log.info("Finished transaction")
    }
}
