package com.example.fcmremoteconfig.fcm

import com.example.fcmremoteconfig.RemoteConfigUtils
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class TestFirebaseMessagingService: FirebaseMessagingService() {

    init {
        FirebaseMessaging.getInstance().subscribeToTopic("REMOTE_CONFIG")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.data["CONFIG_STATE"] == "STALE") {
            println("Remote config stale")
            RemoteConfigUtils.setRemoteConfigStale(applicationContext, true)
        }
    }
}