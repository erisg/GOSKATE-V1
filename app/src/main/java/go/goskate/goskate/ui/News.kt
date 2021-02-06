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
import go.goskate.goskate.helper.CapturePostDialogFragment
import go.goskate.goskate.helper.adapters.NewsAdapter
import go.goskate.goskate.ui.viewmodel.NewsViewModel
import go.goskate.goskate.ui.viewmodel.UserProfileViewModel
import go.goskate.goskate.vo.NewsVO
import kotlinx.android.synthetic.main.news.*

class News : Fragment() {

    private var newsPost: MutableList<NewsVO>? = null
    private val userProfileViewModel: NewsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        addFilesConstraintLayout.visibility = View.GONE

        setOnClickFiles()

        newPostFloatingActionButton.setOnClickListener {
            if (addFilesConstraintLayout.visibility == View.VISIBLE) {
                addFilesConstraintLayout.visibility = View.GONE
            } else {

                addFilesConstraintLayout.visibility = View.VISIBLE
            }
        }

        newsPost?.addAll(userProfileViewModel.newsPost)


        if (newsPost?.isEmpty() == true) {
            newsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
            newsRecyclerView.adapter = NewsAdapter(requireContext(), newsPost!!)
        }

    }


    private fun setOnClickFiles() {
        addFilesFloatingActionButton.setOnClickListener {
            val dialog = CapturePostDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "PostDialog")

        }
    }


}