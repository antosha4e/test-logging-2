package com.antosha4e.bank.service

import com.antosha4e.log.TestLogAppender
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BankServiceLogTest {

    private val bankService: BankService = BankService()

    @Test
    fun `should not contain card number`() {

        // GIVEN
        val cardNumber = "4012888888881882"

        bankService.doTransaction(cardNumber)

        assertFalse(
            logContainsMessage(cardNumber),
            "Logs contains customer card number"
        )
    }


    @Test
    fun `should contain invalid card number`() {

        // GIVEN
        val invalidCardNumber = "4012888_888381882"

        bankService.doTransaction(invalidCardNumber)

        assertTrue(
            logContainsMessage(invalidCardNumber),
            "Logs masked invalid customer card number"
        )
    }


    private fun logContainsMessage(str: String): Boolean {
        return TestLogAppender.getInstance()!!.logContains(BankService::class.java.name, str)
    }
}
