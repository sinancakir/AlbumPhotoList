package io.androidedu.weatherwidget.core.network

import android.annotation.SuppressLint
import android.app.Application

/**
 * Created by sinan on 08.03.2018.
 */

@SuppressLint("Registered")
class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        VolleyService.build(this)
    }
}