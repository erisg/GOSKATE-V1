package go.goskate.goskate.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import go.goskate.goskate.R
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.user_post_image_item.view.*

class UserPostAdapter(val context: Context, val news: MutableList<PostVO>) :
    RecyclerView.Adapter<UserPostAdapter.ViewHolderImage>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderImage {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_post_image_item, parent, false)
        return ViewHolderImage(vista)
    }

    override fun onBindViewHolder(holder: ViewHolderImage, position: Int) {
        holder.bind(news[position])

    }

    override fun getItemCount(): Int {
        return this.news.size
    }


    inner class ViewHolderImage(view: View) : RecyclerView.ViewHolder(view) {

        val postImage: ImageView = view.postImageView
        val postVideo: VideoView = view.postVideoView
        val liked: ImageView = view.likedImageView
        val likedNumber: TextView = view.likedTextView
        val location: ImageView = view.locationImageView

        fun bind(item: PostVO) {
            if (item.typeCapture == PostVO.TypeCapture.PHOTO) {
                postImage.visibility = View.VISIBLE
                postVideo.visibility = View.GONE

                Glide.with(context)
                    .load(item.fileCapture)
                    .into(postImage)

            } else {
                postImage.visibility = View.GONE
                postVideo.visibility = View.VISIBLE

                postVideo.setVideoPath(item.fileCapture)
            }

            likedNumber.text = item.likesNumbers.toString()

            liked.setOnClickListener {

            }

            location.setOnClickListener {

            }

        }
    }
}