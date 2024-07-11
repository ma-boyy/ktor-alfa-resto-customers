package com.alfaresto.plugins

import com.alfaresto.sendNotification
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        sendNotification()
    }
}
