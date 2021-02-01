package go.goskate.goskate.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import go.goskate.goskate.R
import go.goskate.goskate.IMenuGone
import go.goskate.goskate.helper.CapturePostDialogFragment
import kotlinx.android.synthetic.main.new_spot.*

class NewSpot : Fragment() {

    lateinit var menuGone: IMenuGone

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_spot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filesSpotFloatingActionButton.setOnClickListener {
            val dialog = CapturePostDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "PostDialog")
        }

    }


}