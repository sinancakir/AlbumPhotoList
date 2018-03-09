package ovidos.sinan.com.albumphotolist.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ovidos.sinan.com.albumphotolist.R
import ovidos.sinan.com.albumphotolist.holder.PhotoViewHolder
import ovidos.sinan.com.albumphotolist.model.Photo

/**
 * Created by sinan on 09.03.2018.
 */
class PhotoListAdapter(private val context: Context, private val photoList: List<Photo>) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_list_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.progressBar.visibility = View.VISIBLE
        val photo = photoList[position]
        holder.title.text = photo.title

        Glide.with(context).load(photo.thumbnailUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                holder.progressBar.visibility = View.GONE
                return false
            }
        }).into(holder.thumbnail)
    }
}