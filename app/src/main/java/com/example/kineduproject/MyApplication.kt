package com.example.kineduproject

import android.app.Application
import com.example.kineduproject.di.ApplicationComponent
import com.example.kineduproject.di.DaggerApplicationComponent

class MyApplication: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)
}