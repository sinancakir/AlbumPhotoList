package ovidos.sinan.com.albumphotolist.ui

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.androidedu.weatherwidget.core.network.VolleyService
import ovidos.sinan.com.albumphotolist.R
import ovidos.sinan.com.albumphotolist.`interface`.PhotoClickListener
import ovidos.sinan.com.albumphotolist.adapter.PhotoListAdapter
import ovidos.sinan.com.albumphotolist.model.Photo
import java.io.IOException
import java.net.URL


class PhotoActivity : AppCompatActivity(), Response.Listener<String>, Response.ErrorListener, PhotoClickListener {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.activity_photo_recycler_view) }
    private val imageDialog by lazy { AlertDialog.Builder(this).create() }
    private val dialogImage by lazy { imageDialog.findViewById<ImageView>(R.id.custom_dialog_image_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val albumId = intent.getIntExtra("albumId", 0)
        val url = "http://jsonplaceholder.typicode.com/photos/?albumId=$albumId"

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        } else {
            recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }

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

        recyclerView.adapter = PhotoListAdapter(this, photoList, this)
        recyclerView.adapter.notifyDataSetChanged()
    }


    override fun onResponse(response: String) {
        //Log.e(response.javaClass.simpleName, response)

        try {
            castByGSon(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onErrorResponse(error: VolleyError?) {
        VolleyLog.e("onErrorResponse" + error?.message)
    }

    override fun onPhotoItemClickListener(url: String) {

        val layoutInflater = LayoutInflater.from(this)
        val alertView = layoutInflater.inflate(R.layout.custom_dialog, null, false)
        imageDialog.setView(alertView)
        imageDialog.show()
        Glide.with(this).load(url).into(dialogImage!!)
    }

}
