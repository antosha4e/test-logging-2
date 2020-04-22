package com.antosha4e.log

import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.config.Node
import org.apache.logging.log4j.core.config.plugins.Plugin
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory
import org.apache.logging.log4j.core.layout.AbstractStringLayout
import java.nio.charset.Charset
import java.util.regex.Pattern

@Plugin(name = "CardNumberFilteringLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = false)
class CardNumberFilteringLayout(charset: Charset?) : AbstractStringLayout(charset) {

    companion object {

        @JvmStatic
        @PluginBuilderFactory
        fun newBuilder(): Builder {
            return Builder()
        }
    }

    private val MASK = "$1++++++++++++"
    private val PATTERN = Pattern.compile("([0-9]{4})([0-9]{9,15})")


    override fun toSerializable(event: LogEvent?): String {

        if (event == null)
            return ""

        val message = event.message.formattedMessage
        val matcher = PATTERN.matcher(message)

        if (matcher.find()) {

            val maskedMessage = matcher.replaceAll(MASK)

            return maskedMessage
        }

        return ""
    }



    class Builder : org.apache.logging.log4j.core.util.Builder<CardNumberFilteringLayout> {

        override fun build(): CardNumberFilteringLayout {

            return CardNumberFilteringLayout(Charset.defaultCharset())
        }
    }
}
