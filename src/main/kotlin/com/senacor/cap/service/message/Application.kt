package com.senacor.cap.service.message

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import java.time.Instant
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.Entity


@SpringBootApplication
@EnableScheduling
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}



class SomeClient {

    @Autowired
    private val repository: ChatMessageRepository? = null

    fun doSomething() {
    }
}
