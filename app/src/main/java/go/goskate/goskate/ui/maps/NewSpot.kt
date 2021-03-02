package go.goskate.goskate.ui.maps

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import go.goskate.goskate.R
import go.goskate.goskate.customizedviews.NewPostPopUp
import go.goskate.goskate.helper.adapters.NewSpotFilesAdapter
import go.goskate.goskate.helper.dialogs.CaptureFilesNewSpotDialogFragment
import go.goskate.goskate.ui.viewmodel.MapsViewModel
import kotlinx.android.synthetic.main.new_spot.*

class NewSpot : Fragment() {


    private val mapsViewModel: MapsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_spot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageViewNewPost = mutableListOf<Bitmap>()
        mapsViewModel.imagesNewSpot.observe(requireActivity(), {
            imageViewNewPost.add(it)
            if (it != null) {
                photosRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
                photosRecyclerView.adapter = NewSpotFilesAdapter(requireContext(), imageViewNewPost)
            }


        })

        filesSpotFloatingActionButton.setOnClickListener {
            val dialog = CaptureFilesNewSpotDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "PostDialog")
        }

        createButton.setOnClickListener {
            validateInfoComplete()
        }


    }

    private fun validateInfoComplete() {
        val spotName = nameSpotEditText.text.toString()
        val spotHood = hoodEditText.text.toString()
        val spotLocality = localityEditText.text.toString()
        val commentSpot = commentsEditText.text.toString()
        val spotScore = scoreRatingBar.numStars

        val dialog = NewPostPopUp()
        dialog.show(requireActivity().supportFragmentManager, "PostDialog")
//        if (spotName.isNotEmpty() && spotHood.isNotEmpty() && spotLocality.isNotEmpty() && commentSpot.isNotEmpty() && spotScore == 0) {
//            mapsViewModel.spotVO.nameSpot = spotName
//            mapsViewModel.spotVO.nameHood = spotHood
//            mapsViewModel.spotVO.comments = commentSpot
//            mapsViewModel.spotVO.score = spotScore
//            val dialog = NewPostPopUp()
//            dialog.show(requireActivity().supportFragmentManager, "PostDialog")
//        } else {
//            Toast.makeText(context, "FALTA INFORMACION POR INGRESAR", Toast.LENGTH_LONG).show()
//        }
    }

}