package com.senacor.cap.service.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/users/{id}")
    fun greeting(@PathVariable(value = "id") id: String) =
        User(id, "Julia")
}
