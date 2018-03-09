package ovidos.sinan.com.albumphotolist.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import ovidos.sinan.com.albumphotolist.R


/**
 * Created by sinan on 09.03.2018.
 */
class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title by lazy { view.findViewById<TextView>(R.id.activity_photo_title) }
    val thumbnail by lazy { view.findViewById<ImageView>(R.id.activity_photo_thumbnail) }
    val progressBar by lazy { view.findViewById<ProgressBar>(R.id.activity_photo_prgBar) }
}