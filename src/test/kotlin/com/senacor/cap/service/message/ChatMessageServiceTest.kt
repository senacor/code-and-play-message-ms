package com.senacor.cap.service.message

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatMessageServiceTest {
    private val chatMessageRepository = mockk<ChatMessageRepository>()

    private val service = ChatMessageService(chatMessageRepository)

    @Test
    fun fetchChatMessages() {
        val expectedList = listOf(
            ChatMessage("dev", "s@t.de", "Hello"),
            ChatMessage("dev", "s@t.de", "World!")
        )

        every { chatMessageRepository.findAll() } returns expectedList

        val result = service.loadChatMessages("dev")

        assertEquals(2, result.size)
        assertEquals("Hello", result[0].message)
        assertEquals("World!", result[1].message)
    }

}
