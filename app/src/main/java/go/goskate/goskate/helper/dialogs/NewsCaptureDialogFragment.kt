package go.goskate.goskate.helper.dialogs

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.news_capture_dialog.*
import kotlinx.android.synthetic.main.news_capture_dialog.view.*
import java.io.File
import java.io.IOException

class NewsCaptureDialogFragment : DialogFragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var imageLocation: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.news_capture_dialog, container, false)
        val titleNews =
            rootView.postNewsButton.setOnClickListener {

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
            val imageBitmap: Bitmap = BitmapFactory.decodeFile(imageLocation)
            showImageCapture(imageBitmap)
        }
    }

    fun showImageCapture(imageBitmap: Bitmap) {
        captureNewsFileConstraintLayout.visibility = View.GONE
        showCaptureNews.visibility = View.VISIBLE
        newsImageView.setImageBitmap(imageBitmap)
    }

}