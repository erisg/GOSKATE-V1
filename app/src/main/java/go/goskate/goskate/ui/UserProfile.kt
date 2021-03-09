 package go.goskate.goskate.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import go.goskate.goskate.R
import go.goskate.goskate.helper.adapters.UserPostAdapter
import go.goskate.goskate.helper.dialogs.CaptureProfilePhotoDialogFragment
import go.goskate.goskate.ui.viewmodel.UserProfileViewModel
import go.goskate.goskate.vo.PostVO
import go.goskate.goskate.vo.UserVO
import kotlinx.android.synthetic.main.profile.*


 class UserProfile : Fragment() {


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


            userNameTextView.text = userProfileViewModel.getInfoUserProfile().userName
        Picasso.with(context)
            .load(userProfileViewModel.getInfoUserProfile().imageProfile)
                .into(userProfileImageView)


        moreOptionsImageView.setOnClickListener {
            val more = PopupMenu(requireContext(), moreOptionsImageView)
            more.menuInflater.inflate(R.menu.menu_profile, more.menu)
            more.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.config -> {

                    }
                    R.id.logo_out -> {
                        FirebaseAuth.getInstance().signOut()
                        deletePreference()
                        startActivity(Intent(requireContext(), Login::class.java))
                    }
                    R.id.change_profile_image -> {
                        captureImageProfile()
                    }
                }
                true
            }
            more.show()
        }

        allPostUser()

    }

     private fun allPostUser() {
         userProfileViewModel.getAllPostForUser().observe(requireActivity(), { list ->
             userPostRecyclerView.layoutManager =
                 StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
             userPostRecyclerView.adapter = UserPostAdapter(requireContext(), list)
         })
     }

     private fun captureImageProfile() {
         val dialog = CaptureProfilePhotoDialogFragment()
         dialog.show(requireActivity().supportFragmentManager, "PostDialog")

     }

     private fun deletePreference() {
         val prefs = activity?.getSharedPreferences("preference", Context.MODE_PRIVATE)?.edit()
         prefs?.clear()
         prefs?.apply()
     }




 }