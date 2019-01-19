package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")

    /**
     * usage example:
     * Metrics.someCounter.increment()
     */

    internal var message_ms_messages_saved_total = Metrics.counter(prefix + "messages_saved_total")
    internal var message_ms_messages_requests_total = Metrics.counter(prefix + "messages_requests_total")

}
