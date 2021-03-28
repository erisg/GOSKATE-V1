package go.goskate.goskate.ui.maps

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import go.goskate.goskate.R
import go.goskate.goskate.helper.dialogs.NewSpotMapDialogFragment
import go.goskate.goskate.helper.adapters.NewSpotFilesAdapter
import go.goskate.goskate.helper.dialogs.CaptureFilesNewSpotDialogFragment
import go.goskate.goskate.ui.viewmodel.MapsViewModel
import go.goskate.goskate.vo.FileCaptureVO
import kotlinx.android.synthetic.main.new_spot.*

class NewSpot : Fragment() {


    private val mapsViewModel: MapsViewModel by activityViewModels()
    val imageViewNewPost = mutableListOf<FileCaptureVO>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_spot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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


        localitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val position = (p1 as AppCompatTextView).text.toString()
                mapsViewModel.spotVO.locality = position
            }
        }

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> {
                        mapsViewModel.spotVO.category = getString(R.string.street)
                        hourConstraintLayout.visibility = View.GONE
                    }
                    1 -> {
                        mapsViewModel.spotVO.category = getString(R.string.skate_park)
                        hourConstraintLayout.visibility = View.VISIBLE
                    }
                    2 -> {
                        mapsViewModel.spotVO.category = getString(R.string.slides)
                        hourConstraintLayout.visibility = View.GONE
                    }
                    3 -> {
                        mapsViewModel.spotVO.category = getString(R.string.flights)
                        hourConstraintLayout.visibility = View.GONE
                    }
                    4 -> {
                        mapsViewModel.spotVO.category = getString(R.string.vertical)
                        hourConstraintLayout.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun validateInfoComplete() {
        val spotName = nameSpotEditText.text.toString()
        val spotHood = hoodEditText.text.toString()
        val commentSpot = commentsEditText.text.toString()
        val spotScore = scoreRatingBar.numStars

        if (spotName.isNotEmpty() && spotHood.isNotEmpty() && commentSpot.isNotEmpty() && spotScore != 0 && mapsViewModel.imagesNewSpot.value != null) {
            mapsViewModel.spotVO.nameSpot = spotName
            mapsViewModel.spotVO.nameHood = spotHood
            mapsViewModel.spotVO.comments = commentSpot
            mapsViewModel.spotVO.score = spotScore
            mapsViewModel.imagesNewSpot
            imageViewNewPost.forEach { file ->
                mapsViewModel.spotVO.files?.add(file.fileSpot!!)
            }
            val dialog = NewSpotMapDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "PostDialog")
        } else {
            Toast.makeText(context, "FALTA INFORMACION POR INGRESAR", Toast.LENGTH_LONG).show()
        }
    }

}