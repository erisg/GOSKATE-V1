package go.goskate.goskate.helper

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.profile_photo_capture.view.*

class CaptureProfilePhotoDialogFragment : DialogFragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.profile_photo_capture, container, false)


        rootView.openGalleryImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }

        rootView.openCameraPhotoImageView.setOnClickListener {
            takePicture()
        }


        return rootView
    }


    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}