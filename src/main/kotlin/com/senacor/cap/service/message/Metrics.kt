package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"


    var message_ms_messages_saved_total = Metrics.counter(prefix + "messages_saved_total")

    var post_requests = Metrics.counter(prefix + "messages_requests_total", Tags.of( Tag.of("method", "POST")))
    var get_requests = Metrics.counter(prefix + "messages_requests_total", Tags.of(Tag.of("method", "GET")))


    fun incrementPostRequests() {
        post_requests.increment()
    }

    fun incrementGetRequests() {
        get_requests.increment()
    }

    fun incrementSavedMessages() {
        message_ms_messages_saved_total.increment()
    }

}
