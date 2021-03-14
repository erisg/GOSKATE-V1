package go.goskate.goskate.helper.dialogs

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.MapsViewModel
import go.goskate.goskate.vo.PostVO
import kotlinx.android.synthetic.main.custom_dialog_post.view.*
import kotlinx.android.synthetic.main.news_capture_dialog.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CaptureFilesNewSpotDialogFragment : DialogFragment() {

    val REQUEST_IMAGE_CAPTURE = 102
    val REQUEST_PERMISION_CODE = 100
    val REQUEST_VIDEO_CAPTURE = 101
    private val mapsViewModel: MapsViewModel by activityViewModels()
    lateinit var imageLocation: String
    lateinit var image: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.custom_dialog_post, container, false)

        rootView.openVideoConstraintLayout.setOnClickListener {
            permission().observe(viewLifecycleOwner, Observer {
                if (it) {
                    selectImageProfile()
                }
            })
        }

        rootView.openCameraPhotoConstraintLayout.setOnClickListener {
            permission().observe(viewLifecycleOwner, Observer {
                if (it) {
                    takePicture()
                }
            })
        }

        rootView.openVideoConstraintLayout.setOnClickListener {
            permission().observe(viewLifecycleOwner, Observer {
                if (it) {
                }
            })
        }
        return rootView

    }

    private fun selectImageProfile() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {

        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val uri = Uri.fromFile(File(imageLocation))
            mapsViewModel.imagesNewSpot.value = uri
            dismiss()
        }
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

    private fun createImageFile(): File {
        // Create an image file name
        val imageName = "post_"
        val storageDir: File = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val image = File.createTempFile(imageName, ".jpg", storageDir)
        imageLocation = image.absolutePath

        return image
    }


    private fun permission(): MutableLiveData<Boolean> {
        val permissionResponse = MutableLiveData<Boolean>()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            permissionResponse.value = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_IMAGE_CAPTURE
            )
        }
        return permissionResponse
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
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