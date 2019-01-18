package com.senacor.cap.service.message

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI
import java.net.URISyntaxException

@RunWith(SpringRunner::class)
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

        assertEquals(expected, result.body)
        verify { serviceMock.loadChatMessages("dev") }
    }

    @Test
    @Throws(URISyntaxException::class)
    fun newChatMessageTest() {
        val savedMessage = ChatMessage("dev", "sender@test.de", "Hello World!", id = 123)
        every { serviceMock.saveChatMessage(any()) } returns savedMessage

        val result = controller.newChatMessage(ChatMessage("dev", "sender@test.de", "Hello World!"))

        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertEquals(URI("/api/channels/dev/messages/123"), result.headers.location)
        verify { serviceMock.saveChatMessage(ChatMessage("dev", "sender@test.de", "Hello World!")) }
    }

    @Test
    fun deleteMessageByValidId() {
        val channel = "dev"
        val id = 123L
        val savedMessage = ChatMessage("dev", "sender@test.de", "Hello World!", id)
        every { serviceMock.saveChatMessage(any()) } returns savedMessage

        every { serviceMock.deleteById(123) } returns true
        val result = controller.deleteMessageOfChannel(channel, id)

        assertEquals(HttpStatus.OK, result.statusCode)
        verify { serviceMock.deleteById(id) }
    }

    @Test
    fun deleteMessageByInvalidId() {
        val channel = "dev"
        val id = 123L
        val savedMessage = ChatMessage("dev", "sender@test.de", "Hello World!", id)
        every { serviceMock.saveChatMessage(any()) } returns savedMessage

        every { serviceMock.deleteById(321) } returns false
        val result = controller.deleteMessageOfChannel(channel, 321)

        assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
        verify { serviceMock.deleteById(321) }
    }
}