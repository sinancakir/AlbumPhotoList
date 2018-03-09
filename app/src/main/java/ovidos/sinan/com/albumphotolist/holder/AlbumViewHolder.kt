package ovidos.sinan.com.albumphotolist.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import ovidos.sinan.com.albumphotolist.R

/**
 * Created by sinan on 08.03.2018.
 */

class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtAlbumList by lazy { view.findViewById<TextView>(R.id.album_list_item_txtAlbum) }
}