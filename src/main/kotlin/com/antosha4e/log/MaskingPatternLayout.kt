package com.antosha4e.log

import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.config.Node
import org.apache.logging.log4j.core.config.plugins.Plugin
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory
import org.apache.logging.log4j.core.config.plugins.PluginElement
import org.apache.logging.log4j.core.layout.AbstractStringLayout
import org.apache.logging.log4j.core.layout.PatternLayout
import java.nio.charset.Charset
import java.util.regex.Pattern

@Plugin(name = "MaskingPatternLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = false)
class MaskingPatternLayout(
    charset: Charset?,
    private val patternLayout: PatternLayout
) : AbstractStringLayout(charset) {

    companion object {

        private const val CARD_MASK = "$1++++++++++++"
        private val CARD_PATTERN = Pattern.compile("([0-9]{4})([0-9]{9,15})")

        @JvmStatic
        @PluginBuilderFactory
        fun newBuilder(): MaskedBuilder {
            return MaskedBuilder()
        }
    }


    override fun toSerializable(event: LogEvent?): String {

        if (event == null)
            return ""

        val formattedMessage = patternLayout.toSerializable(event)
        val matcher = CARD_PATTERN.matcher(formattedMessage)

        return if (matcher.find())
            matcher.replaceAll(CARD_MASK)
        else
            formattedMessage
    }


    class MaskedBuilder : org.apache.logging.log4j.core.util.Builder<MaskingPatternLayout> {

        @PluginElement("PatternLayout")
        private var patternLayout: PatternLayout? = null

        @PluginBuilderAttribute
        private var pattern = PatternLayout.DEFAULT_CONVERSION_PATTERN

        fun withPatternLayout(patternLayout: PatternLayout?): MaskedBuilder {
            this.patternLayout = patternLayout
            return this
        }

        fun withPattern(pattern: String?): MaskedBuilder {
            this.pattern = pattern!!
            return this
        }

        override fun build(): MaskingPatternLayout {

            return MaskingPatternLayout(
                Charset.defaultCharset(),
                patternLayout ?: PatternLayout.newBuilder().withPattern(pattern).build()
            )
        }
    }
}
