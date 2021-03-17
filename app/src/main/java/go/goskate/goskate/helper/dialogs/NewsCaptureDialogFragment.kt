package go.goskate.goskate.helper.dialogs

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.NewsViewModel
import go.goskate.goskate.ui.viewmodel.UserProfileViewModel
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.news_capture_dialog.*
import kotlinx.android.synthetic.main.news_capture_dialog.view.*
import java.io.File
import java.io.IOException

class NewsCaptureDialogFragment : DialogFragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var imageLocation: String
    private val newsViewModel: NewsViewModel by activityViewModels()
    lateinit var image: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.news_capture_dialog, container, false)

        rootView.postNewsButton.setOnClickListener {
            saveNews()
        }

        rootView.galleryImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivity(intent)
        }

        rootView.takePictureImageView.setOnClickListener {
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


        return rootView
    }

    private fun saveNews() {
        val titleNews = titleNewsTextView.text.toString()
        val descriptionNews = descriptionNewsTextView.text.toString()
        if (titleNews.isNotEmpty() && descriptionNews.isNotEmpty()) {
            newsViewModel.postVO.captureFile = image.toString()
            newsViewModel.postVO.captureType = PostVO.TypeCapture.PHOTO
            newsViewModel.postVO.title = titleNews
            newsViewModel.postVO.description = descriptionNews
            newsViewModel.postVO.typePost = PostVO.TypePost.NEWS
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
    }


    private fun createImageFile(): File {
        // Create an image file name
        val imageName = "news_"
        val storageDir: File = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val image = File.createTempFile(imageName, ".jpg", storageDir)
        imageLocation = image.absolutePath
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val uri = Uri.fromFile(File(imageLocation))
            image = uri
            showImageCapture(uri)
        }
    }

    private fun showImageCapture(imageBitmap: Uri) {
        captureNewsFileConstraintLayout.visibility = View.GONE
        showCaptureNews.visibility = View.VISIBLE
        newsImageView.setImageURI(imageBitmap)
    }

}