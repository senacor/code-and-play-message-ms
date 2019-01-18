package com.senacor.cap.service.message

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.http.HttpStatus
import java.net.URI
import java.net.URISyntaxException

class ChatMessageControllerTest {


    private var serviceMock = mockk<ChatMessageService>()

    private var controller = ChatMessageController(serviceMock)


    @Test
    fun loadChannelMessages() {
        val expected = listOf(
            ChatMessage("dev", "s1", "m1"),
            ChatMessage("dev", "s2", "m2")
        )
        every { serviceMock.loadChatMessages("dev") } returns expected

        val result = controller.loadChatMessages("dev")

        assertEquals(expected, result)
        verify { serviceMock.loadChatMessages("dev") }
    }



    @Test
    @Throws(URISyntaxException::class)
    fun newChatMessagesTest() {
        val savedMessage = ChatMessage("dev", "sender@test.de", "Hello World!", id = 123)
        every { serviceMock.saveChatMessage(any(), any(), any()) } returns savedMessage

        val result = controller.newChatMessages("dev", ChatMessage("dev", "sender@test.de", "Hello World!"))

      //  assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(HttpStatus.CREATED, result. statusCode)
        assertEquals(URI("/api/channels/dev/messages/123"), result.headers.location)
        verify { serviceMock.saveChatMessage("dev", "sender@test.de", "Hello World!") }
    }



    @Test
    fun boostSelfConfidence() {
    }

}