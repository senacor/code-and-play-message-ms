package com.senacor.cap.service.message

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

const val CHAT_MESSAGES_PATH ="/api/channels/{channelId}/messages"

@RestController
@RequestMapping(CHAT_MESSAGES_PATH)
class ChatMessageController ( val service: ChatMessageService) {

    @GetMapping
    fun loadChatMessages(@PathVariable("channelId")channelId: String): List<ChatMessage>{
        Metrics.requests_total_get.increment()
        val result = service.loadChatMessages(channelId)
        return result
    }

    @PostMapping
    fun newChatMessage(@PathVariable("channelId")channelId: String, @RequestBody message: ChatMessage ): ResponseEntity<ChatMessage>{
        Metrics.requests_total_post.increment()
        val result = service.saveChatMessage(channelId, message.sender, message.message)

        val location =UriComponentsBuilder.newInstance().path(CHAT_MESSAGES_PATH)
                .pathSegment(result.id!!.toString()).buildAndExpand(channelId)
        return ResponseEntity.created(location.toUri()).build()
    }

}