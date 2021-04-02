package go.goskate.goskate.helper.adapters

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import go.goskate.goskate.R
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.android.synthetic.main.pop_up_go_skate.*

class NewsAdapter(val context: Context, val news: List<PostVO>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var isChecked = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return this.news.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imagePost: ImageView = view.findViewById(R.id.newsImageView)
        private val videoPost: VideoView = view.findViewById(R.id.newsVideoView)
        private val location: TextView = view.findViewById(R.id.titleNewsTextView)
        private val description: TextView = view.findViewById(R.id.moreInfoTextView)
        private val imageProfile: ImageView = view.findViewById(R.id.imageProfileImageView)
        private val userName: TextView = view.findViewById(R.id.userTextView)
        private val likedImage: ImageView = view.findViewById(R.id.likedImageView)
        private val likedImageRed: ImageView = view.findViewById(R.id.likedImageViewRed)

        fun bind(item: PostVO) {
            userName.text = item.nameUser
            Glide.with(context)
                .load(item.profileImageUser)
                .into(imageProfile)
            if (item.captureType == PostVO.TypeCapture.PHOTO) {
                videoPost.visibility = View.GONE
                imagePost.visibility = View.VISIBLE
                Glide.with(context)
                    .load(item.captureFile)
                    .into(imagePost)
            } else {
                val mediaController = MediaController(context)
                mediaController.setAnchorView(videoPost)
                imagePost.visibility = View.GONE
                videoPost.visibility = View.VISIBLE
                val video = Uri.parse(item.captureFile)
                videoPost.setMediaController(mediaController)
                videoPost.setVideoURI(video)
                videoPost.requestFocus()
                videoPost.setOnPreparedListener {
                    it.start()
                }

            }

            likedImage.setOnClickListener {
                likedImage.visibility = View.GONE
                likedImageRed.visibility = View.VISIBLE

            }

            likedImageRed.setOnClickListener {
                likedImageRed.visibility = View.GONE
                likedImage.visibility = View.VISIBLE

            }

            location.text = item.title
            description.text = item.description
        }
    }

}