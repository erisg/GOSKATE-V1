package go.goskate.goskate.ui.maps

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import go.goskate.goskate.R
import go.goskate.goskate.helper.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.spot.view.*


class SpotBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    companion object {
        fun newInstance(): SpotBottomSheetFragment = SpotBottomSheetFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.spot, container)
        viewPager = rootView.findViewById(R.id.infoPostViewPager)
        tabLayout = rootView.findViewById(R.id.titleInfoTabLayout)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter
    }


}
