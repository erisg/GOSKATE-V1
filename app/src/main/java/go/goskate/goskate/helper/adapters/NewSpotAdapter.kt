package go.goskate.goskate.helper.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.new_spot_file_item.view.*
import kotlinx.android.synthetic.main.news_item.view.*

class NewSpotAdapter(val context: Context, val newSpot: List<Bitmap>) :
    RecyclerView.Adapter<NewSpotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newSpot[position])
    }

    override fun getItemCount(): Int {
        return this.newSpot.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagePost = view.newSpotImageView

        fun bind(item: Bitmap) {
            imagePost.setImageBitmap(item)
        }


    }
}