package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")

    internal val message_ms_messages_saved_total = Metrics.counter(prefix + "message_ms_messages_saved_total")//- Zählt wie oft die save-Methode des ChatMessageServices aufgerufen wird.
    internal val message_ms_messages_requests_totalget = Metrics.counter(prefix + "message_ms_messages_request_total", Tags.of(Tag.of("method", "GET")))
    internal val message_ms_messages_requests_totalpost = Metrics.counter(prefix + "message_ms_messages_request_total", Tags.of(Tag.of("method", "POST")))
    // - Zählt die API Request. Verwende ein tag um zwischen POST und GET Request zu unterscheiden.

//    Tags.of(Tag.of("method", "GET")
    /**
     * usage example:
     * Metrics. message_ms_messages_saved_total .increment()
     */
}
