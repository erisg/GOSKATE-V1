package go.goskate.goskate.helper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.UserProfileViewModel
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.custom_dialog_post.view.*
import java.io.File
import java.util.jar.Manifest

class CustomDialogFragment : DialogFragment() {


    val REQUEST_VIDEO_CAPTURE = 101
    val REQUEST_WRITE_EXTERNAL = 2
    val userProfileViewModel: UserProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.custom_dialog_post, container, false)


        rootView.openGalleryImageView.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivity(intent)
        }

        rootView.openCameraPhotoImageView.setOnClickListener {

        }

        rootView.openVideoImageView.setOnClickListener {
            recordVideo()
        }
        return rootView
    }

    private fun recordVideo() {

        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, REQUEST_VIDEO_CAPTURE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val videoUri = data?.data
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            userProfileViewModel.postVO.fileCapture = videoUri.toString()
            userProfileViewModel.postVO.typeCapture = PostVO.TypeCapture.VIDEO
        }
        dismiss()
    }
}
