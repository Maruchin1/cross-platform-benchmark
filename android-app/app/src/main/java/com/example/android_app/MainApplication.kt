package com.example.android_app

import android.app.Application

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DI.init(applicationContext)
    }
}