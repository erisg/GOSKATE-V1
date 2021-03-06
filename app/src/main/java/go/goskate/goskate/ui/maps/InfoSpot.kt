package go.goskate.goskate.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.info_spot.*

class InfoSpot : Fragment() {

    companion object {
        fun newInstance(): InfoSpot = InfoSpot()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_spot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moreInfoHourImageView.setOnClickListener {
            recyclerView.visibility = View.VISIBLE
        }
    }
}