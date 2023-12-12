package com.fabianodev.voluntiers

import android.app.Application
import com.fabianodev.voluntiers.di.ApplicationComponent
import com.fabianodev.voluntiers.di.DaggerApplicationComponent
import com.google.firebase.FirebaseApp


class AppApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}