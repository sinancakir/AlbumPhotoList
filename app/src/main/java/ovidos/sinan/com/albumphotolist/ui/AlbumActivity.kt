package ovidos.sinan.com.albumphotolist.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.androidedu.weatherwidget.core.network.VolleyService
import ovidos.sinan.com.albumphotolist.R
import ovidos.sinan.com.albumphotolist.`interface`.AlbumClickListener
import ovidos.sinan.com.albumphotolist.adapter.AlbumListAdapter
import ovidos.sinan.com.albumphotolist.model.Album
import java.io.IOException

class AlbumActivity : AppCompatActivity(), Response.Listener<String>, Response.ErrorListener, AlbumClickListener {

    private val url = "http://jsonplaceholder.typicode.com/albums"

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.activity_album_recycler_view) }
    private val progress by lazy { findViewById<ProgressBar>(R.id.activity_album_prgBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(divider)

        sendRequest(url)
        progress.visibility = View.GONE
    }

    private fun sendRequest(url: String) {
        val request = StringRequest(Request.Method.GET, url, this, this)
        VolleyService.build(this).requestQueue.add(request)
        VolleyService.build(this).requestQueue.start()
    }

    private fun castByGSon(response: String) {
        val albumResponse = object : TypeToken<List<Album>>() {}.type
        val albumList = Gson().fromJson<List<Album>>(response, albumResponse)

        recyclerView.adapter = AlbumListAdapter(albumList, this)
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

    override fun onAlbumItemClickListener(albumId: Int) {
        val intent = Intent(this, PhotoActivity::class.java)
        intent.putExtra("albumId", albumId)
        startActivity(intent)
    }
}
