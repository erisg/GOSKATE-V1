package go.goskate.goskate.ui.maps

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import go.goskate.goskate.R


class SpotBottomSheetFragment : BottomSheetDialogFragment() {


    companion object {
        fun newInstance(): SpotBottomSheetFragment = SpotBottomSheetFragment()
        const val TAG = "CustomBottomSheetDialogFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.spot, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBottomSheetBehaviorCallback.onStateChanged(
            this.requireView(),
            BottomSheetBehavior.STATE_HIDDEN
        )
    }


    private val mBottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                BottomSheetBehavior.STATE_HIDDEN
            }

        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
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
