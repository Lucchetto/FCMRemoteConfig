package com.example.fcmremoteconfig.fcm

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class TestFirebaseMessagingService: FirebaseMessagingService() {

    init {
        FirebaseMessaging.getInstance().subscribeToTopic("REMOTE_CONFIG")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        println(message)
        super.onMessageReceived(message)
    }
}