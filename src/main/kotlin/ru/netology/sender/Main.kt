package ru.netology.sender

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
//        .putData("action", "NEW_POST")
//        .putData("content", """{
//          "postId": 1,
//          "content": "content",
//          "postAuthor": "Netology"
//        }""".trimIndent())
        .setToken(token)
        .build()


    val newPostMessage = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "postId": 1,
          "content": "content",
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
    FirebaseMessaging.getInstance().send(newPostMessage)

}