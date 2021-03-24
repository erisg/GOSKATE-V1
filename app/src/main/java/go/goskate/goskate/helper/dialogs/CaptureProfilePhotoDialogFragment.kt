package go.goskate.goskate.helper.dialogs

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.profile_photo_capture.*
import kotlinx.android.synthetic.main.profile_photo_capture.view.*
import java.io.File
import java.io.IOException

class CaptureProfilePhotoDialogFragment : DialogFragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_PERMISION_CODE = 100
    lateinit var imageLocation: String
    private val authViewModel: AuthViewModel by activityViewModels()
    lateinit var imageUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.profile_photo_capture, container, false)

        rootView.galleryImageView.setOnClickListener {
            permissionGallery()
        }

        rootView.takePictureImageView.setOnClickListener {
            permission()
        }

        rootView.changePictureButton.setOnClickListener {
            changePictureProfile()
        }


        return rootView
    }

    private fun selectImageProfile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val uri = Uri.fromFile(File(imageLocation))
            authViewModel.profileImage.value = uri
            showImageCapture(uri)
        }
    }

    private fun changePictureProfile() {
        authViewModel.changePictureProfile(imageUri)
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

    fun showImageCapture(image: Uri) {
        captureProfileFileConstraintLayout.visibility = View.GONE
        showCaptureNews.visibility = View.VISIBLE
        profileImageView.setImageURI(image)
        imageUri = image
    }

    private fun createImageFile(): File {
        // Create an image file name
        val imageName = "profile_"
        val storageDir: File = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val image = File.createTempFile(imageName, ".jpg", storageDir)
        imageLocation = image.absolutePath

        return image
    }

    private fun permissionGallery() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            selectImageProfile()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_IMAGE_CAPTURE
            )
        }
    }

    private fun permission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            takePicture()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_IMAGE_CAPTURE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (REQUEST_PERMISION_CODE == requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "permiso concedido", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "permiso denegado", Toast.LENGTH_LONG).show()
            }
        }
    }
}
