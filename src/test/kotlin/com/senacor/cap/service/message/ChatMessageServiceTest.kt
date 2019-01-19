package com.senacor.cap.service.message

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant

class ChatMessageServiceTest {

    private val channelServiceMock = mockk<ChannelService>()

    private val chatMessageRepository = mockk<ChatMessageRepository>()

    private val service = ChatMessageService(channelServiceMock, chatMessageRepository)

    @Test
    fun fetchChatMessages() {
        every { channelServiceMock.existsChannel("dev") } returns true
        val expectedList = listOf(
                ChatMessage("dev", "s@t.de", "Hello", Instant.now()),
                ChatMessage("dev", "s@t.de", "World!", Instant.now())
        )

        every { chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc("dev") } returns expectedList

        val result = service.loadChatMessages("dev")

        assertEquals(2, result.size)
        assertEquals("Hello", result[0].message)
        assertEquals("World!", result[1].message)
    }
}
