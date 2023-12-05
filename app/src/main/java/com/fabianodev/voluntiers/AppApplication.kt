package com.fabianodev.voluntiers

import android.app.Application
import com.fabianodev.voluntiers.rest.api.di.ApplicationComponent
import com.fabianodev.voluntiers.rest.api.di.DaggerApplicationComponent

class AppApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)
}