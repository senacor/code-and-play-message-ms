package com.senacor.cap.service.message

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags



object Metrics {

    private const val prefix = "message_ms_"

    //internal val someCounter = Metrics.counter(prefix + "some_counter_total")
    internal val requestCounterPost = Metrics.counter(prefix + "GET_messages_requests_total",Tags.of(Tag.of("method","POST")))
    internal val requestCounterGet = Metrics.counter(prefix + "POST_messages_requests_total",Tags.of(Tag.of("method","GET")))
    internal val saveCounter = Metrics.counter(prefix + "message_ms_messages_saved_total")

    fun incrementGetRequests() {
        requestCounterGet.increment()
    }

    fun incrementPostRequests(){
        requestCounterPost.increment()
    }

    fun incrementSaveCounter(){
        saveCounter.increment()
    }


        /**
     * usage example:
     * Metrics.someCounter.increment()
     */
}
