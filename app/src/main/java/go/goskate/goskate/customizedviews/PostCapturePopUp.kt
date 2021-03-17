package go.goskate.goskate.customizedviews

import android.app.Activity
import android.content.Intent
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
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.NewsViewModel
import go.goskate.goskate.ui.viewmodel.UserProfileViewModel
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.pop_up_go_skate.*
import kotlinx.android.synthetic.main.pop_up_go_skate.view.*
import kotlinx.android.synthetic.main.profile.*
import java.io.File
import java.io.IOException

class PostCapturePopUp() : DialogFragment() {


    private val REQUEST_VIDEO_CAPTURE = 101
    val REQUEST_IMAGE_CAPTURE = 1
    private val newsViewModel: NewsViewModel by activityViewModels()
    lateinit var imageLocation: String
    lateinit var image: Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.pop_up_go_skate, container, false)
        rootView.postButton.setOnClickListener {
            savePost()
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

    fun savePost() {
        val title = locationEditText.text.toString()
        val description = descriptionEditText.text.toString()
        newsViewModel.postVO.title = title
        newsViewModel.postVO.description = description
        newsViewModel.postVO.captureType = PostVO.TypeCapture.PHOTO
        newsViewModel.postVO.captureFile = image.toString()
        newsViewModel.postVO.typePost = PostVO.TypePost.POST
        newsViewModel.getInfoUserProfile().observe(viewLifecycleOwner, {
            newsViewModel.postVO.profileImageUser = it.imageProfile!!
            newsViewModel.postVO.nameUser = it.userName
        })
        newsViewModel.setInfoPost().observe(viewLifecycleOwner, { result ->
            if (result == "Successful") {
                dismiss()
            } else {
                Toast.makeText(requireContext(), result, Toast.LENGTH_LONG).show()
            }
        })


    }

    private fun recordVideo() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, REQUEST_VIDEO_CAPTURE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {

        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageUri = Uri.fromFile(File(imageLocation))
            image = imageUri
            showCapture(imageUri)
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


    fun showCapture(imageUri: Uri) {
        typeCaptureConstraintLayout.visibility = View.GONE
        showCaptureImageConstraintLayout.visibility = View.VISIBLE
        postImageImageView.setImageURI(imageUri)
    }
}
