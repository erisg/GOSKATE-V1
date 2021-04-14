package go.goskate.goskate.helper.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.stringjjj.view.*

class fileAdapter(val context: Context, val newSpot: List<String>) :
    RecyclerView.Adapter<fileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.stringjjj, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newSpot[position])
    }

    override fun getItemCount(): Int {
        return this.newSpot.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagePost: TextView = view.textoo

        fun bind(item: String) {
            imagePost.text = item
        }


    }
}