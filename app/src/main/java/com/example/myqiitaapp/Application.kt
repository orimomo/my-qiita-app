package com.example.myqiitaapp

import com.example.myqiitaapp.di.KoinModule
import org.koin.android.ext.android.startKoin

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        // DIの設定
        startKoin(this, KoinModule.modules)
    }
}