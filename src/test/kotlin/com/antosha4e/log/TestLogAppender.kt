package com.antosha4e.log

import org.apache.logging.log4j.core.AbstractLifeCycle
import org.apache.logging.log4j.core.Filter
import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.appender.AbstractAppender
import org.apache.logging.log4j.core.config.Property
import org.apache.logging.log4j.core.config.plugins.Plugin
import org.apache.logging.log4j.core.config.plugins.PluginAttribute
import org.apache.logging.log4j.core.config.plugins.PluginElement
import org.apache.logging.log4j.core.config.plugins.PluginFactory
import org.apache.logging.log4j.core.layout.PatternLayout

// note: class name need not match the @Plugin name.
@Plugin(
    name = "TestLogAppender",
    category = "Core",
    elementType = "appender",
    printObject = true
)
class TestLogAppender(
    name: String?,
    filter: Filter?,
    private val patternLayout: Layout<String>,
    ignoreExceptions: Boolean
) : AbstractAppender(name, filter, patternLayout, ignoreExceptions, Property.EMPTY_ARRAY) {

    private val events = mutableListOf<LogEvent>()


    override fun append(event: LogEvent) {

        val eventCopy = event.toImmutable()

        events.add(eventCopy)

        // To see in console what was logged
        print(layout.toSerializable(eventCopy))
    }

    override fun getLayout(): Layout<String> {
        return patternLayout
    }


    fun getEvent(loggerName: String, index: Int): LogEvent? {

        return events.filter { loggerName == it.loggerName }.getOrNull(index)
    }


    fun logContains(loggerName: String, message: String): Boolean {

        return events
            .filter { loggerName == it.loggerName }
            .map { layout.toSerializable(it) }
            .any { it.contains(message) }
    }


    companion object {

        private var instance: TestLogAppender? = null


        fun getInstance(): TestLogAppender? {
            return instance
        }


        private fun getInstance(
            name: String?,
            filter: Filter?,
            layout: Layout<String>
        ): TestLogAppender? {

            if (instance == null) {
                instance = TestLogAppender(name, filter, layout, true)
            }

            return instance
        }

        // Your custom appender needs to declare a factory method
        // annotated with `@PluginFactory`. Log4j will parse the configuration
        // and call this factory method to construct an appender instance with
        // the configured attributes.
        @JvmStatic
        @PluginFactory
        fun createAppender(
            @PluginAttribute("name") name: String?,
            @PluginElement("Layout") layout: Layout<String>?,
            @PluginElement("Filter") filter: Filter?,
            @PluginAttribute("otherAttribute") otherAttribute: String?
        ): TestLogAppender? {
            var patternLayout = layout

            if (name == null) {
                AbstractLifeCycle.LOGGER.error("No name provided for TestLogAppender")
                return null
            }

            if (layout == null) {
                patternLayout = PatternLayout.createDefaultLayout()
            }

            return getInstance(name, filter, patternLayout!!)
        }
    }
}
