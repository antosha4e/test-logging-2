package com.antosha4e.bank.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BankService {

    private val log: Logger = LoggerFactory.getLogger(BankService::class.java)

    fun doTransaction() {

        println("AAA")

        log.info("Test")
        log.info("Test {}", 1)
    }
}
