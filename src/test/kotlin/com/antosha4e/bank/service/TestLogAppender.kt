package com.antosha4e.bank.service

import org.apache.logging.log4j.core.AbstractLifeCycle
import org.apache.logging.log4j.core.Filter
import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.appender.AbstractAppender
import org.apache.logging.log4j.core.config.plugins.Plugin
import org.apache.logging.log4j.core.config.plugins.PluginAttribute
import org.apache.logging.log4j.core.config.plugins.PluginElement
import org.apache.logging.log4j.core.config.plugins.PluginFactory
import org.apache.logging.log4j.core.layout.PatternLayout
import java.io.Serializable
import java.util.*
import java.util.stream.Collectors

// note: class name need not match the @Plugin name.
@Plugin(
    name = "TestLogAppender",
    category = "Core",
    elementType = "appender",
    printObject = true
)
class TestLogAppender : AbstractAppender {
    private val events: MutableList<LogEvent> =
        ArrayList()

    protected constructor(
        name: String?,
        filter: Filter?,
        layout: Layout<out Serializable?>?
    ) : super(name, filter, layout) {
    }

    protected constructor(
        name: String?,
        filter: Filter?,
        layout: Layout<out Serializable?>?,
        ignoreExceptions: Boolean
    ) : super(name, filter, layout, ignoreExceptions) {
    }

    override fun append(event: LogEvent) {
        events.add(event.toImmutable())
    }

    fun getEvent(loggerName: String, index: Int): LogEvent? {
        val newEvents = events.stream()
            .filter { lg: LogEvent -> loggerName == lg.loggerName }
            .collect(
                Collectors.toList()
            )
        return if (newEvents.size < index) null else newEvents[index]
    }

    companion object {
        var instance: TestLogAppender? = null
            private set

        fun getInstance(
            name: String?,
            filter: Filter?,
            layout: Layout<out Serializable?>?,
            ignoreExceptions: Boolean
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
        @PluginFactory
        fun createAppender(
            @PluginAttribute("name") name: String?,
            @PluginElement("Layout") layout: Layout<out Serializable?>?,
            @PluginElement("Filter") filter: Filter?,
            @PluginAttribute("otherAttribute") otherAttribute: String?
        ): TestLogAppender? {
            var layout = layout
            if (name == null) {
                AbstractLifeCycle.LOGGER.error("No name provided for TestLogAppender")
                return null
            }
            if (layout == null) {
                layout = PatternLayout.createDefaultLayout()
            }
            return getInstance(name, filter, layout, true)
        }
    }
}
