package go.goskate.goskate.helper.adapters

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.spot_file_item.view.*

class NewSpotFilesAdapter(val context: Context, val newSpot: List<Uri>) :
    RecyclerView.Adapter<NewSpotFilesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.spot_file_item, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newSpot[position])
    }

    override fun getItemCount(): Int {
        return this.newSpot.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagePost = view.fileSpotImageView

        fun bind(item: Uri) {
            imagePost.setImageURI(item)
        }


    }
}