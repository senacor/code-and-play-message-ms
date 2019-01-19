package com.senacor.cap.service.message

import com.sun.deploy.net.HttpResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.concurrent.atomic.AtomicLong


class Greeting(val id: Long, val content: String)

@RestController
class GreetingController {
    private val counter = AtomicLong()

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting {
        return Greeting(counter.incrementAndGet(),
                String.format(template, name))
    }

    companion object {

        private val template = "Hello, %s!"
    }
}

@RestController
class ChatMessageController (val service: ChatMessageService) {


    @GetMapping(path = ["/api/channels/{channelId}/messages"])
    fun loadChatMessages(@PathVariable channelId: String): List<ChatMessage> {
        val result = service.loadChatMessages(channelId)
        //Metrics.incrementRequests()
        return result
    }


    @PostMapping(path = arrayOf("/api/channels/{channelId}/messages"))
    fun newChatMessages(@PathVariable channelId: String, @RequestBody body: ChatMessage): ResponseEntity<ChatMessage> {
        val result = service.saveChatMessage(channelId, body.sender, body.message)
        //Metrics.incrementRequests()
        return ResponseEntity.created(URI("/api/channels/$channelId/messages/${result.id}")).build()
    }


}