package go.goskate.goskate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import go.goskate.goskate.R
import go.goskate.goskate.helper.CustomDialogFragment
import go.goskate.goskate.helper.UserPostAdapter
import go.goskate.goskate.ui.viewmodel.UserProfileViewModel
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.profile.*

class UserProfile : Fragment() {

    var posts = mutableListOf<PostVO>()
    private val userProfileViewModel: UserProfileViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (posts.size > 0) {
            chargeUserPost()
        }


        openTakeFilesImageView.setOnClickListener {
            val dialog = CustomDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "PostDialog")
        }

    }

    private fun chargeUserPost() {
        userPostRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        userPostRecyclerView.adapter = UserPostAdapter(requireContext(), posts)
    }

    override fun onResume() {
        super.onResume()
        if (isAdded) {
            posts.add(userProfileViewModel.postVO)
            chargeUserPost()
        }
    }


}