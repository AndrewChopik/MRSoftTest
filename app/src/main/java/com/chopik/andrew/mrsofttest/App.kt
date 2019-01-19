package com.chopik.andrew.mrsofttest

import android.app.Application
import com.chopik.andrew.mrsofttest.di.AppComponent
import com.chopik.andrew.mrsofttest.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}