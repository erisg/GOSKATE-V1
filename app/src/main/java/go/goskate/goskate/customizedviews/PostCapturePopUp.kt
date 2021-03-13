package go.goskate.goskate.customizedviews

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.NewsViewModel
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.news_capture_dialog.*
import kotlinx.android.synthetic.main.news_capture_dialog.newsImageView
import kotlinx.android.synthetic.main.pop_up_go_skate.*
import kotlinx.android.synthetic.main.pop_up_go_skate.view.*
import java.io.File
import java.io.IOException

class PostCapturePopUp() : DialogFragment() {


    private val REQUEST_VIDEO_CAPTURE = 101
    val REQUEST_IMAGE_CAPTURE = 1
    private val newsViewModel: NewsViewModel by activityViewModels()
    lateinit var imageLocation: String
    lateinit var image: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.pop_up_go_skate, container, false)
        val postLocation = rootView.locationEditText.text.toString()
        val postDescription = rootView.descriptionEditText.text.toString()
        rootView.postButton.setOnClickListener {
            newsViewModel.postVO.fileCapture = image.toString()
            newsViewModel.postVO.title = postLocation
            newsViewModel.postVO.description = postDescription
            newsViewModel.setInfoPost()
        }

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
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            var imageLocation: File? = null
            try {
                imageLocation = createImageFile()
            } catch (ex: IOException) {
                Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
            }
            if (imageLocation != null) {
                val imageUri = FileProvider.getUriForFile(
                    requireContext(),
                    "go.goskate.goskate.contentprovider",
                    imageLocation
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }

        }
    }

    private fun recordVideo() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, REQUEST_VIDEO_CAPTURE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {

        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap: Bitmap = BitmapFactory.decodeFile(imageLocation)
            image = imageBitmap
            showCapture(imageBitmap)
        }
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun createImageFile(): File {
        // Create an image file name
        val imageName = "news_"
        val storageDir: File = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val image = File.createTempFile(imageName, ".jpg", storageDir)
        imageLocation = image.absolutePath

        return image
    }


    fun showCapture(imageBitmap: Bitmap) {
        typeCaptureConstraintLayout.visibility = View.GONE
        showCaptureImageConstraintLayout.visibility = View.VISIBLE
        postImageImageView.setImageBitmap(imageBitmap)
    }
}
