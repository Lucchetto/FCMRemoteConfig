package com.example.fcmremoteconfig

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class RemoteConfigUtils {

    companion object {
        private const val REMOTE_CONFIG_STALE_PREF_KEY = "remote_config_stale"

        fun setupPeriodicSync() {
            val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
        }

        fun testPeriodicFetch() {
            val remoteConfig = Firebase.remoteConfig

            remoteConfig.fetchAndActivate().addOnCompleteListener {
                println("test config periodic update ${remoteConfig.getString("test")}")
            }
        }

        fun testFCMSync(context: Context) {
            val remoteConfig = Firebase.remoteConfig
            remoteConfig.fetch(0).onSuccessTask {
                remoteConfig.activate()
            }.addOnCompleteListener {
                setRemoteConfigStale(context, false)
                println("test config fcm update ${remoteConfig.getString("test")}")
            }
        }

        fun setRemoteConfigStale(context: Context, stale: Boolean) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(REMOTE_CONFIG_STALE_PREF_KEY, stale)
                .apply()
        }

        fun getRemoteConfigStale(context: Context): Boolean =
            PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(REMOTE_CONFIG_STALE_PREF_KEY, true)
    }
}