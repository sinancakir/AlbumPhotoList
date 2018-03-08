package io.androidedu.weatherwidget.core.network

import android.annotation.SuppressLint
import android.app.Application


/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 10.02.2018                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/

@SuppressLint("Registered")
class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        VolleyService.build(this)
    }
}