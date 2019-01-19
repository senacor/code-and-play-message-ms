package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")

    internal val savedTotal = Metrics.counter(prefix + "messages_saved_total")
    internal val postTotal = Metrics.counter(prefix + "messages_requests")
    internal val getTotal = Metrics.counter(prefix + "messages_requests")

    /**
     * usage example:
     * Metrics.someCounter.increment()
     */
}
