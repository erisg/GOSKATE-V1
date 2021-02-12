package go.goskate.goskate.ui.maps

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import go.goskate.goskate.R
import go.goskate.goskate.IMenuGone
import go.goskate.goskate.helper.adapters.NewSpotAdapter
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
                photosRecyclerView.adapter = NewSpotAdapter(requireContext(), imageViewNewPost)
            }
        })

        filesSpotFloatingActionButton.setOnClickListener {
            val dialog = CaptureFilesNewSpotDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "PostDialog")
        }

    }

}