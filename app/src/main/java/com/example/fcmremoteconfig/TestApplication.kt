package com.example.fcmremoteconfig

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging

class TestApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (!RemoteConfigUtils.checkPlayServices(this)) {
            RemoteConfigUtils.setupPeriodicSync()
            RemoteConfigUtils.fetchPeriodic()
        } else {
            if (RemoteConfigUtils.isRemoteConfigStale(this)) {
                RemoteConfigUtils.fetchFCM(applicationContext)
            }
        }

        println("register remote config topic")
        FirebaseMessaging.getInstance().subscribeToTopic("REMOTE_CONFIG").addOnCompleteListener {
            println("complete")
        }.addOnSuccessListener {
            println("success")
        }.addOnFailureListener {
            println("failure")
        }
    }
}