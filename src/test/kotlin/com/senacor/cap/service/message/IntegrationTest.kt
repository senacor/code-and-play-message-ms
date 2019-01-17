package com.senacor.cap.service.message

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited

@Inherited
@ActiveProfiles("test")
@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    classes = [Application::class]
)
annotation class IntegrationTest
