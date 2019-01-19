package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    var message_ms_messages_saved_total = Metrics.counter(prefix + "message_ms_messages_saved_total")

    var message_ms_messages_requests_GET_total = Metrics.counter(prefix + "message_ms_messages_requests_total", Tags.of(Tag.of("method", "GET")))
    var message_ms_messages_requests_POST_total = Metrics.counter(prefix + "message_ms_messages_requests_total", Tags.of(Tag.of("method", "POST")))

            fun  incrementSaves() {
        message_ms_messages_saved_total.increment()
    }
    fun  incrementGETRequests() {
        message_ms_messages_requests_GET_total.increment()
    }
        fun  incrementPOSTRequests(){
            message_ms_messages_requests_POST_total.increment()
}
}
