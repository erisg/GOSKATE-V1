package go.goskate.goskate.ui.maps

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import go.goskate.goskate.R
import go.goskate.goskate.helper.adapters.ViewPagerAdapter


class SpotBottomSheetFragment : BottomSheetDialogFragment() {


    private var navController: NavController? = null
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    companion object {
        fun newInstance(): SpotBottomSheetFragment = SpotBottomSheetFragment()
        const val TAG = "CustomBottomSheetDialogFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewSpot = inflater.inflate(R.layout.spot, container, false)
        tabLayout = viewSpot.findViewById(R.id.titleInfoTabLayout)
        viewPager = viewSpot.findViewById(R.id.InfoPostViewPager)
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(InfoSpot.newInstance(), "Info")
        adapter.addFragment(FilesSpot.newInstance(), "FOTOS-VIDEOS")
        adapter.addFragment(CommentSpots.newInstance(), "COMENTARIOS")
        viewPager.adapter = adapter
        return viewSpot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    private fun chargeSpot(viewpager: ViewPager) {

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
