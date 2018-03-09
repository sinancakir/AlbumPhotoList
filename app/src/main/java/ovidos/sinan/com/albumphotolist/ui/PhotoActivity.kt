package ovidos.sinan.com.albumphotolist.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.androidedu.weatherwidget.core.network.VolleyService
import ovidos.sinan.com.albumphotolist.R
import ovidos.sinan.com.albumphotolist.adapter.PhotoListAdapter
import ovidos.sinan.com.albumphotolist.model.Photo
import java.io.IOException



class PhotoActivity : AppCompatActivity(), Response.Listener<String>, Response.ErrorListener {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.activity_photo_recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val albumId = intent.getIntExtra("albumId", 0)
        val url = "http://jsonplaceholder.typicode.com/photos/?albumId=$albumId"

        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)
        sendRequest(url)

    }

    private fun sendRequest(url: String) {
        val request = StringRequest(Request.Method.GET, url, this, this)
        VolleyService.build(this).requestQueue.add(request)
        VolleyService.build(this).requestQueue.start()
    }

    private fun castByGSon(response: String) {
        val photoResponse = object : TypeToken<List<Photo>>() {}.type
        val photoList = Gson().fromJson<List<Photo>>(response, photoResponse)

        recyclerView.adapter = PhotoListAdapter(this, photoList)
        recyclerView.adapter.notifyDataSetChanged()
    }


    override fun onResponse(response: String) {
        Log.e(response.javaClass.simpleName, response)

        try {
            castByGSon(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onErrorResponse(error: VolleyError?) {
        VolleyLog.e("onErrorResponse" + error?.message)
    }
}
