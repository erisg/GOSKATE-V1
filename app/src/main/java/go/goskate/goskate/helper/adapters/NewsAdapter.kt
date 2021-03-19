package go.goskate.goskate.helper.adapters

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
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
        val imagePost = view.newsImageView
        val videoPost = view.newsVideoView
        val location = view.titleNewsTextView
        val description = view.moreInfoTextView
        val imageProfile = view.imageProfileImageView
        val userName = view.userTextView

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

            location.text = item.title
            description.text = item.description
        }

    }

}