package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val savedMessages = Metrics.counter(prefix + "messages_saved_total")

    private val messagesRequests = prefix + "messages_requests_total"

    internal val messagesGetRequests = Metrics.counter(messagesRequests, Tags.of(Tag.of("method", "GET")))
    internal val messagesPostRequests = Metrics.counter(messagesRequests, Tags.of(Tag.of("method", "POST")))

}
