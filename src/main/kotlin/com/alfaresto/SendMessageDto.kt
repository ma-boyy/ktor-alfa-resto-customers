package com.alfaresto

import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification

@kotlinx.serialization.Serializable
data class SendMessageDto(
    val to: String?,
    val notification: NotificationBody
)

@kotlinx.serialization.Serializable
data class SendMessageIosDto(
    val to: String?,
    val title: String?,
    val body: String?,
    val link: String
)

@kotlinx.serialization.Serializable
data class NotificationBody(
    val title: String,
    val body: String,
    val link: String
)

fun SendMessageDto.toMessage(): Message {
    return Message.builder()
        .setToken(to)
        .putData("link", notification.link)
        .setNotification(
            Notification.builder()
                .setTitle(notification.title)
                .setBody(notification.body)
                .build()
        )
        .build()
}

fun SendMessageIosDto.toMessage(): Message {
    return Message.builder()
        .setToken(to)
        .putData("link", link)
        .putData("title", title)
        .putData("body", body)
        .build()
}