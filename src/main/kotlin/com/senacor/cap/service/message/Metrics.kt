package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics

object Metrics {

    private const val prefix = "message_ms_"

    internal val someCounter = Metrics.counter(prefix + "some_counter_total")

  //  Metrics.someCounter.increment()

}
