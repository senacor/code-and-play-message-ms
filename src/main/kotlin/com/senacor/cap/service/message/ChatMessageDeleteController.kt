package com.senacor.cap.service.message

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


const val MESSAGES_PATH = "/api/channels/{channelId}/messages/{messageId}"

@RestController
@RequestMapping(MESSAGES_PATH)
class ChatMessageDeleteController(
        private val serviceMock: ChatMessageService) {

    @DeleteMapping
    fun deleteChatMessage(@PathVariable("channelId") channelId: String, @PathVariable("messageId") messageId: String) {

        val mesId: Long
        mesId = messageId.toLong()

        serviceMock.deleteChatMessage(channelId, mesId)
    }


}
