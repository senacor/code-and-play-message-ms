package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")

    internal val saved_total = Metrics.counter(prefix + "messages_saved_total")

    internal val requests_total_get = Metrics.counter(prefix + "messages_requests_total", Tags.of(Tag.of("messages_requests_total", "GET")))

    internal val requests_total_post = Metrics.counter(prefix + "messages_requests_total", Tags.of(Tag.of("method", "POST")))
    /**
     * usage example:
     * Metrics.someCounter.increment()
     */
}
