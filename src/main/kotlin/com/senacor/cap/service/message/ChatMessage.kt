package com.senacor.cap.service.message

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ChatMessage(var channel: String, val sender: String, val message: String, val creationTimestamp: Instant?) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long? = null

    constructor(channel: String, sender: String, message: String) : this(channel, sender, message, Instant.now())

    constructor(channel: String, sender: String, message: String, id: Long) : this(channel, sender, message,
            Instant.now()) {
        this.id = id
    }

    fun getId(): Long? = id

    override fun equals(other: Any?): Boolean {
        if (other != null && other is ChatMessage) {
            val ldtOther = LocalDateTime.ofInstant(other.creationTimestamp, ZoneOffset.UTC)
            val ldtThis = LocalDateTime.ofInstant(this.creationTimestamp, ZoneOffset.UTC)
            return other.channel.equals(this.channel) && other.sender.equals(this.sender)
                    && other.message.equals(this.message) && ldtOther.year.equals(ldtThis.year)
                    && ldtOther.month.equals(ldtThis.month)
                    && ldtOther.dayOfMonth.equals(ldtThis.dayOfMonth)
                    && ldtOther.hour.equals(ldtThis.hour)
                    && ldtOther.minute.equals(ldtThis.minute)
                    && ldtOther.second.equals(ldtThis.second)
        }
        return false
    }
}