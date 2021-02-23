package go.goskate.goskate.ui.maps

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.spot.*


class SpotBottomSheetFragment : BottomSheetDialogFragment() {


    private var navController: NavController? = null

    companion object {
        fun newInstance(): SpotBottomSheetFragment = SpotBottomSheetFragment()
        const val TAG = "CustomBottomSheetDialogFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.spot, container, false)
        view.setBackgroundColor(Color.TRANSPARENT)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleInfoTabLayout

    }

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    private var mListener: ItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface ItemClickListener {
        fun onItemClick(item: String)
    }

}
