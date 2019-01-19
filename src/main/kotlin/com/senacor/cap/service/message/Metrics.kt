package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics

object Metrics {

    private const val prefix = "message_ms_"

    internal val messageCreateCounter = Metrics.counter(prefix + "message_create")

    internal val messageLoadCounter = Metrics.counter(prefix + "message_saved_total")

    internal val messageDeleteCounter = Metrics.counter(prefix + "message_delete")

    internal val apiCounterPost = Metrics.counter(prefix + "requests_total", "http_method", "POST")
    internal val apiCounterGet = Metrics.counter(prefix + "requests_total", "http_method", "GET")
    internal val apiCounterDelete = Metrics.counter(prefix + "requests_total", "http_method", "DELETE")

    /**
     * usage example:
     * Metrics.someCounter.increment()
     */
}
