package ovidos.sinan.com.albumphotolist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ovidos.sinan.com.albumphotolist.R
import ovidos.sinan.com.albumphotolist.`interface`.AlbumClickListener
import ovidos.sinan.com.albumphotolist.holder.AlbumViewHolder
import ovidos.sinan.com.albumphotolist.model.Album

/**
 * Created by sinan on 08.03.2018.
 */
class AlbumListAdapter(private var albumList: List<Album>, private val albumClickListener: AlbumClickListener) : RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_list_item, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumList[position]
        holder.txtAlbumList.text = album.title
        holder.itemView.setOnClickListener{albumClickListener.onAlbumItemClickListener(album.id)}
    }

    override fun getItemCount(): Int = albumList.size

}