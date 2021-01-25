package go.goskate.goskate.ui.maps

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import go.goskate.goskate.R


class SpotBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): SpotBottomSheetFragment = SpotBottomSheetFragment()
        const val TAG = "CustomBottomSheetDialogFragment"

    }



    private fun setUpViews() {

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
