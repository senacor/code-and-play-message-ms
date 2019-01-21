package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val messages_saved_total = Metrics.counter(prefix + "messages_saved_total")

    private val messagesRequests:String = prefix + "messages_requests_total"

    internal val messages_requests_get = Metrics.counter(messagesRequests + Tags.of(Tag.of("method", "GET")))
    internal val messages_requests_post = Metrics.counter(messagesRequests + Tags.of(Tag.of("method", "POST")))



    /**
     * usage example:
     * Metrics.someCounter.increment()
     */
}
