package com.senacor.cap.service.message

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant

@IntegrationTest
@RunWith(SpringRunner::class)
class ChatMessageRepositoryIT {

    @Autowired
    private lateinit var repository: ChatMessageRepository

    @Before
    fun setup() {
        // ensure we are staring without any chat messages
        repository.deleteAll()
    }

    @After
    fun cleanup() {
        repository.deleteAll()
    }

    @Test
    fun saveAndLoadChatMessages() {
        assertTrue(repository.findAll().count() == 0)

        val timeOne = Instant.now().minusSeconds(35)
        val messageOne = repository.save(ChatMessage("dev", "sender@test.de", "Hello", timeOne))

        assertEquals(1, repository.findAll().count())

        val timeTwo = Instant.now()
        val messageTwo = repository.save(ChatMessage("dev", "sender@test.de", "World!", timeTwo))

        val result = repository.findAll()

        assertEquals(listOf(messageOne, messageTwo), result)
    }

    @Test
    fun saveAndLoadThreeMessages() {
        assertTrue(repository.findAll().count() == 0)
        repository.save(ChatMessage("general", "sender@test.de", "let's"))
        repository.save(ChatMessage("general", "sender@test.de", "test"))
        repository.save(ChatMessage("general", "sender@test.de", "mongo"))

        val messages = repository.findAll()

        assertEquals(3, messages.count())
    }

}