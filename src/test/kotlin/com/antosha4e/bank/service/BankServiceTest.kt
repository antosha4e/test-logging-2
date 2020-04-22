package com.antosha4e.bank.service

import org.apache.logging.log4j.message.Message
import org.junit.Assert
import org.junit.Test

class CoffeeServiceTest {
    private val coffeeService: BankService = BankService()
/*
    @Test
    fun testLogOutputNaive() {
        val baristaName = "Stavros Domatiotis"
        val coffeeName = "Flat White"
        coffeeService.makeCoffee(baristaName, coffeeName)
        assertTrue(
            "Wrong formatted message",
            getEventMessage(0).formattedMessage == String.format(
                "Requested coffee [%s] from barista [%s]",
                coffeeName,
                baristaName
            )
        )
        assertTrue(
            "Wrong formatted message",
            getEventMessage(1).formattedMessage == String.format(
                "Barista [%s] made a coffee [%s]",
                baristaName,
                coffeeName
            )
        )
    }

    @Test
    fun testLogOutputFull() {
        val baristaName = "Stavros Domatiotis"
        val coffeeName = "Flat White"
        coffeeService.makeCoffee(baristaName, coffeeName)
        val logMessage1 = getEventMessage(0)
        val expectedFormatedMessage1 =
            String.format("Requested coffee [%s] from barista [%s]", coffeeName, baristaName)
        assertNotNull("Log message is null", logMessage1)
        assertTrue(
            "Wrong formatted message",
            logMessage1.formattedMessage == expectedFormatedMessage1
        )
        assertTrue("Wrong parameters size", logMessage1.parameters.size == 2)
        assertTrue("Wrong 1 param", logMessage1.parameters[0] == coffeeName)
        assertTrue("Wrong 2 param", logMessage1.parameters[1] == baristaName)
        val logMessage2 = getEventMessage(1)
        val expectedFormatedMessage2 =
            String.format("Barista [%s] made a coffee [%s]", baristaName, coffeeName)
        assertNotNull("Log message is null", logMessage2)
        assertTrue(
            "Wrong formatted message",
            logMessage2.formattedMessage == expectedFormatedMessage2
        )
        assertTrue("Wrong parameters size", logMessage2.parameters.size == 2)
        assertTrue("Wrong 1 param", logMessage2.parameters[0] == baristaName)
        assertTrue("Wrong 2 param", logMessage2.parameters[1] == coffeeName)
    }


    private fun getEventMessage(eventIndex: Int): Message {
        return TestLogAppender.getInstance()!!.getEvent(BankService::class.java.getName(), eventIndex)!!.message
    }
    */
}
