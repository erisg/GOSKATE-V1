package go.goskate.goskate.helper.adapters

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import go.goskate.goskate.R
import go.goskate.goskate.helper.dialogs.CaptureFilesNewSpotDialogFragment
import go.goskate.goskate.helper.dialogs.ShowFileForSpot
import go.goskate.goskate.vo.FileCaptureVO
import kotlinx.android.synthetic.main.spot_file_item.view.*

class NewSpotFilesAdapter(val context: Context, val newSpot: List<FileCaptureVO>) :
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
        val imagePost: ImageView = view.fileSpotImageView
        val videoPost: ImageView = view.fileSpotVideo

        fun bind(item: FileCaptureVO) {
            if (item.typePost == "IMAGE") {
                videoPost.visibility = View.GONE
                imagePost.visibility = View.VISIBLE
                imagePost.setImageURI(item.fileSpot)
            } else {
                imagePost.visibility = View.GONE
                videoPost.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {

            }
        }


    }
}