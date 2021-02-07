package go.goskate.goskate.helper.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import go.goskate.goskate.R
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.news_item.view.*

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
        val location = view.titleNewsTextView.text.toString()
        val description = view.moreInfoTextView.text.toString()

        fun bind(item: PostVO) {
            Picasso.with(context)
                .load(item.fileImageCapture)
                .into(imagePost)
        }


    }
}