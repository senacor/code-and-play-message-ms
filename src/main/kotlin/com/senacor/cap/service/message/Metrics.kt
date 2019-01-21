package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")

    internal val messages_saved_total = Metrics.counter(prefix + "messages_saved_total")

    internal val messages_requests_total_GET = Metrics.counter(prefix + "messages_requests_total", Tags.of(Tag.of("method", "GET")))

    internal val messages_requests_total_POST = Metrics.counter(prefix + "messages_requests_total", Tags.of(Tag.of("method", "POST")))

    /**
     * usage example:
     * Metrics.someCounter.increment()
     */
}
