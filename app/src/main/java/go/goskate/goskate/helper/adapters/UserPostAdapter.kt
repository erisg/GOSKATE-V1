package go.goskate.goskate.helper.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import go.goskate.goskate.R
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.post_item_profile.view.*

class UserPostAdapter(val context: Context, val news: MutableList<PostVO>) :
    RecyclerView.Adapter<UserPostAdapter.ViewHolderImage>() {

    lateinit var mediaController: MediaController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderImage {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item_profile, parent, false)
        mediaController = MediaController(context)
        return ViewHolderImage(vista)
    }

    override fun onBindViewHolder(holder: ViewHolderImage, position: Int) {
        holder.bind(news[position])

    }

    override fun getItemCount(): Int {
        return this.news.size
    }


    inner class ViewHolderImage(view: View) : RecyclerView.ViewHolder(view) {
        val imagePost = view.newsImageView
        val videoPost = view.newsVideoView


        fun bind(item: PostVO) {
            if (item.typeCapture == PostVO.TypeCapture.PHOTO) {
                videoPost.visibility = View.GONE
                imagePost.visibility = View.VISIBLE
                Picasso.with(context)
                    .load(item.fileCapture)
                    .into(imagePost)
            } else {
                imagePost.visibility = View.GONE
                videoPost.visibility = View.VISIBLE
                videoPost.setVideoURI(item.fileCapture!!.toUri())
                videoPost.setMediaController(mediaController)
                videoPost.start()
            }
        }
    }
}