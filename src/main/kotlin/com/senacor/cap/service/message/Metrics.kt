package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags

object Metrics {

    private const val prefix = "message_ms_"

    internal val countMsgSavedTotal = Metrics.counter(prefix + "messages_saved_total")
    internal val countRequestPostTotal = Metrics.counter(prefix + "messages_requests_total",
                                                     Tags.of(Tag.of("method", "GET")))
    internal val countRequestGetTotal = Metrics.counter(prefix + "messages_requests_total",
                                                     Tags.of(Tag.of("method", "GET")))
}
