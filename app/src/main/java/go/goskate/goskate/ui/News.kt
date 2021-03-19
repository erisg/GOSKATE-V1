package go.goskate.goskate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import go.goskate.goskate.R
import go.goskate.goskate.helper.dialogs.NewsCaptureDialogFragment
import go.goskate.goskate.customizedviews.PostCapturePopUp
import go.goskate.goskate.helper.adapters.NewsAdapter
import go.goskate.goskate.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.news.*
import kotlinx.android.synthetic.main.profile.*

class News : Fragment() {


    private val newsViewModel: NewsViewModel by activityViewModels()


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

        getAllPost()

        newPostFloatingActionButton.setOnClickListener {
            if (addFilesConstraintLayout.visibility == View.VISIBLE) {
                addFilesConstraintLayout.visibility = View.GONE
            } else {
                addFilesConstraintLayout.visibility = View.VISIBLE
            }
        }

        addFilesFloatingActionButton.setOnClickListener {
            addFilesConstraintLayout.visibility = View.GONE
            val dialog = PostCapturePopUp()
            dialog.show(requireActivity().supportFragmentManager, "PostDialog")

        }

        addNewsFloatingActionButton.setOnClickListener {
            addFilesConstraintLayout.visibility = View.GONE
            val dialog = NewsCaptureDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "NewsDialog")
        }
    }

    private fun getAllPost() {
        newsViewModel.getAllPost().observe(requireActivity(), {
            newsRecyclerView?.layoutManager = GridLayoutManager(requireContext(), 1)
            val adapter = NewsAdapter(this.context!!, it)
            newsRecyclerView?.adapter = adapter


        })
    }


}