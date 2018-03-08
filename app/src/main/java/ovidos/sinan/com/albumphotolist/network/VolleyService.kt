package io.androidedu.weatherwidget.core.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 10.02.2018                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/

class VolleyService constructor(private val context: Context) {

    private constructor(builder: VolleyService.Builder) : this(context = builder.context)

    val requestQueue: RequestQueue by lazy { Volley.newRequestQueue(context) }

    companion object {
        fun build(context: Context) = VolleyService.Builder(context.applicationContext).build()
    }

    class Builder(val context: Context) {

        fun build() = VolleyService(this)
    }
}