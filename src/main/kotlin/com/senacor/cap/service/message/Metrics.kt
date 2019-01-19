package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags
import org.springframework.http.HttpMethod

object Metrics {

    private const val prefix = "message_ms_"

    internal val saveCounter = Metrics.counter(prefix + "messages_saved_total")

    internal val apiCallCounterGet = Metrics.counter(prefix + "messages_requests_total", Tags.of(Tag.of("method", HttpMethod.GET.name)))
    internal val apiCallCounterPost = Metrics.counter(prefix + "messages_requests_total", Tags.of(Tag.of("method", HttpMethod.POST.name)))



    /**
     * usage example:
     * Metrics.someCounter.increment()
     */
}
