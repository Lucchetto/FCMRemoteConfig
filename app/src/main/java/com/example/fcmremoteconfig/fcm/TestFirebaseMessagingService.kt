package com.example.fcmremoteconfig.fcm

import com.example.fcmremoteconfig.RemoteConfigUtils
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class TestFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    init {
        println("start FirebaseMessagingService")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.data["CONFIG_STATE"] == "STALE") {
            println("Remote config stale")
            RemoteConfigUtils.setRemoteConfigStale(applicationContext, true)
        }
    }
}