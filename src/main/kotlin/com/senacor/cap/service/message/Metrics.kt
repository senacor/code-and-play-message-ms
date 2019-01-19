package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")

    /**
     * usage example:
     * Metrics.someCounter.increment()
     */

    internal var message_ms_messages_saved_total = Metrics.counter(prefix + "messages_saved_total")

    internal var message_ms_messages_requests_post = Metrics.counter(prefix + "messages_requests_total", Tags.of("method", "POST"))

    internal var message_ms_messages_requests_get = Metrics.counter(prefix + "messages_requests_total", Tags.of("method", "GET"))

}
