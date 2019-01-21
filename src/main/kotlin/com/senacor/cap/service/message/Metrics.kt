package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")
    internal val saveTotal = Metrics.counter(prefix + "messages_saved_total")
    internal val requestGetTotal = Metrics.counter(prefix + "messages_request_total", Tags.of(Tag.of("method", "GET")))
    internal val requestPostTotal = Metrics.counter(prefix + "messages_request_total", Tags.of(Tag.of("method", "POST")))

}
