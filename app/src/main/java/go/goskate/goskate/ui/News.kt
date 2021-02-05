package go.goskate.goskate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import go.goskate.goskate.R
import go.goskate.goskate.helper.adapters.NewsAdapter
import go.goskate.goskate.ui.viewmodel.NewsViewModel
import go.goskate.goskate.ui.viewmodel.UserProfileViewModel
import go.goskate.goskate.vo.NewsVO
import kotlinx.android.synthetic.main.news.*

class News : Fragment() {

    private var newsPost: MutableList<NewsVO>? = null
    private val userProfileViewModel: NewsViewModel by activityViewModels()
    private var clicked = false

    // Animaciones del floatingButton
   // private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        addFilesFloatingActionButton.visibility = View.GONE
        addNewsFloatingActionButton.visibility = View.GONE

        newPostFloatingActionButton.setOnClickListener {
            addFilesFloatingActionButton.visibility = View.VISIBLE
            addNewsFloatingActionButton.visibility = View.VISIBLE
        }

        newsPost?.addAll(userProfileViewModel.newsPost)


        if (newsPost?.isEmpty() == true) {
            newsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
            newsRecyclerView.adapter = NewsAdapter(requireContext(), newsPost!!)
        }

    }


    private fun onAddButtonClicked(){
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked:Boolean) {
        TODO("Not yet implemented")
    }

    private fun setVisibility(clicked:Boolean) {
       if(!clicked){
           addFilesFloatingActionButton.visibility = View.VISIBLE
           addNewsFloatingActionButton.visibility = View.VISIBLE
       }else{
           addFilesFloatingActionButton.visibility = View.GONE
           addNewsFloatingActionButton.visibility = View.GONE
       }
    }
}