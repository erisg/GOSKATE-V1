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
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.android.synthetic.main.profile.*
import java.lang.reflect.Array.get

class NewsAdapter(val context: Context, val news: List<PostVO>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    lateinit var mediaController: MediaController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)

        mediaController = MediaController(context)
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

            location.text = item.location
            description.text = item.description


        }


    }
}