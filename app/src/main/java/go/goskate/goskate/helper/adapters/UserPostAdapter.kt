package go.goskate.goskate.helper.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import go.goskate.goskate.R
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.user_post_image_item.view.*

class UserPostAdapter(val context: Context, val news: MutableList<PostVO>) :
    RecyclerView.Adapter<UserPostAdapter.ViewHolderImage>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderImage {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item_profile, parent, false)
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
        val location = view.titleNewsTextView
        val description = view.moreInfoTextView


        fun bind(item: PostVO) {
            if (item.typeCapture == PostVO.TypeCapture.PHOTO) {
                imagePost.visibility = View.VISIBLE

                Glide.with(context)
                    .load(item.fileImageCapture)
                    .into(imagePost)

            }

            description.text = item.description
            location.text = item.location

        }
    }
}