package go.goskate.goskate.helper

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.UserProfileViewModel
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.custom_dialog_post.view.*

class CustomDialogFragment : DialogFragment() {


    private val REQUEST_VIDEO_CAPTURE = 101
    val REQUEST_IMAGE_CAPTURE = 1
    private val userProfileViewModel: UserProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.custom_dialog_post, container, false)


        rootView.openGalleryConstraintLayout.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivity(intent)
        }

        rootView.openCameraPhotoConstraintLayout.setOnClickListener {
            takePicture()
        }

        rootView.openVideoConstraintLayout.setOnClickListener {
            recordVideo()
        }
        return rootView
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun recordVideo() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, REQUEST_VIDEO_CAPTURE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val dataUri = data?.data
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            userProfileViewModel.postVO.fileVideoCapture = dataUri.toString()
            userProfileViewModel.postVO.typeCapture = PostVO.TypeCapture.VIDEO
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("post") as Bitmap
            userProfileViewModel.postVO.fileImageCapture = imageBitmap
            userProfileViewModel.postVO.typeCapture = PostVO.TypeCapture.PHOTO
        }
        dismiss()
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}
