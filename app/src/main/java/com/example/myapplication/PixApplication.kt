package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.cux.analytics_sdk.CuxAnalytics

@HiltAndroidApp
class PixApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        CuxAnalytics.init(this, "1428112091-0-86456300-1480349278-583c565e0fc5d")
    }
}
