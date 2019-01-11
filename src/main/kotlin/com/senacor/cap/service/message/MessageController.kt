package com.senacor.cap.service.message

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController {

    @GetMapping("/api/channels/{channelId}/messages")
    fun greeting(@PathVariable(value = "channelId") channelId: String) =
        MessageResponse(listOf(Message(channelId, "hello world")))
}
