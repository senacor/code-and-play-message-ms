package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.web.util.UriComponents

const val CHAT_MESSAGES_PATH = "/api/channels/{channelId}/messages"

@RestController
@RequestMapping(CHAT_MESSAGES_PATH)
class ChatMessageController ( var service: ChatMessageService) {

    @GetMapping
    fun loadChatMessages(@PathVariable("channelId") channelId : String): List<ChatMessage>{
        Metrics.requests_total_get.increment()
        val result = service.loadChatMessages(channelId)
        return result
    }

    @PostMapping
    fun newChatMessage(@PathVariable("channelId") channel: String, @RequestBody message: ChatMessage ): ResponseEntity<ChatMessage>{
        Metrics.requests_total_post.increment()
        val result = service.saveChatMessage(channel, message.sender, message.message)

        val location:UriComponents = UriComponentsBuilder.newInstance().path(CHAT_MESSAGES_PATH)
                .pathSegment(result.id!!.toString()).buildAndExpand(channel)
        return ResponseEntity.created(location.toUri()).build()
    }

}