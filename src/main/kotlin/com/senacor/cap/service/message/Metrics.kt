package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics

object Metrics {

    private const val prefix = "message_ms_"

    internal val savedMessages = Metrics.counter(prefix + "messages_saved_total")
    internal val requestMessages =  Metrics.counter(prefix + "messages_request_total")



    /**
     * usage example:
     * Metrics.someCounter.increment()
     */
}
