package go.goskate.goskate.ui.maps

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import go.goskate.goskate.R
import go.goskate.goskate.helper.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.spot.*


class SpotBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    companion object {
        fun newInstance(): SpotBottomSheetFragment = SpotBottomSheetFragment()

    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val viewSpot = View.inflate(context, R.layout.spot, null)
        dialog.setContentView(viewSpot)
        viewPager = viewSpot.findViewById(R.id.InfoPostViewPager)
        tabLayout = viewSpot.findViewById(R.id.titleInfoTabLayout)
        chargeData()
    }

    private fun chargeData() {
        tabLayout.addTab(tabLayout.newTab().setText("INFORMACION"))
        tabLayout.addTab(tabLayout.newTab().setText("FOTOS-VIDEOS"))
        tabLayout.addTab(tabLayout.newTab().setText("COMENTARIOS"))
     //   viewPager.adapter = ViewPagerAdapter(activity!!.supportFragmentManager, tabLayout.tabCount)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL


    }

}
