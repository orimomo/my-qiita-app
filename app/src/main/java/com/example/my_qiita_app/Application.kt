package com.example.my_qiita_app

import com.example.my_qiita_app.di.KoinModule
import org.koin.android.ext.android.startKoin

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        // DIの設定
        startKoin(this, KoinModule.modules)
    }
}