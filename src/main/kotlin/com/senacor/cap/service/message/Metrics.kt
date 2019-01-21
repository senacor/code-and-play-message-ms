package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")
    internal val saveCounter = Metrics.counter(prefix + "saved_total")
    internal val totalRequestGetCounter = Metrics.counter(prefix + "request_total","method", "GET")
    internal val totalRequestPostCounter = Metrics.counter(prefix + "request_total","method", "POST")
    /**
     * usage example:
     * Metrics.someCounter.increment()
     */

}
