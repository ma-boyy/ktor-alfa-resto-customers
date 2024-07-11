package com.alfaresto

import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification

@kotlinx.serialization.Serializable
data class ChatMessageDto(
    val to: String?,
    val notification: NotificationChat,
)

@kotlinx.serialization.Serializable
data class NotificationChat(
    val username: String,
    val body: String
)

fun ChatMessageDto.toMessage(): Message {
    return Message.builder()
        .setToken(to)
        .setNotification(
            Notification.builder()
                .setTitle(notification.username)
                .setBody(notification.body)
                .build()
        )
        .build()
}