package go.goskate.goskate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import go.goskate.goskate.R
import go.goskate.goskate.customizedviews.PostCapturePopUp
import go.goskate.goskate.helper.adapters.NewsAdapter
import go.goskate.goskate.helper.dialogs.CapturePostDialogFragment
import go.goskate.goskate.helper.dialogs.CaptureProfilePhotoDialogFragment
import go.goskate.goskate.ui.viewmodel.NewsViewModel
import go.goskate.goskate.vo.NewsVO
import kotlinx.android.synthetic.main.news.*

class News : Fragment() {


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
        getAllPost()

        newPostFloatingActionButton.setOnClickListener {
            if (addFilesConstraintLayout.visibility == View.VISIBLE) {
                addFilesConstraintLayout.visibility = View.GONE
            } else {
                addFilesConstraintLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun getAllPost() {
        userProfileViewModel.getAllPost().observe(requireActivity(), {
            newsRecyclerView?.layoutManager = GridLayoutManager(requireContext(), 1)
            newsRecyclerView?.adapter = NewsAdapter(requireContext(), it)
        })
    }

    private fun setOnClickFiles() {
        addFilesFloatingActionButton.setOnClickListener {
            val dialog = PostCapturePopUp()
            dialog.show(requireActivity().supportFragmentManager, "PostDialog")

        }
    }


}