package com.alfaresto

import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.apache.commons.logging.Log

fun Route.sendNotification() {
    route("/send") {
        post {
            val body = call.receiveNullable<SendMessageIosDto>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                println(HttpStatusCode.BadRequest)
                return@post
            }

            println(body.toString())

            FirebaseMessaging.getInstance().send(body.toMessage())

            call.respond(HttpStatusCode.OK)
        }
    }

    route("/send-android") {
        post {
            val body = call.receiveNullable<SendMessageDto>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            FirebaseMessaging.getInstance().send(body.toMessage())

            call.respond(HttpStatusCode.OK)
        }
    }

    route("/chat") {
        post {
            val body = call.receiveNullable<ChatMessageDto>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            FirebaseMessaging.getInstance().send(body.toMessage())

            call.respond(HttpStatusCode.OK)
        }
    }
}