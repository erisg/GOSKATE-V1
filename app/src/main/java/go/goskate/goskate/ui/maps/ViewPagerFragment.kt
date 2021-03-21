package go.goskate.goskate.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import go.goskate.goskate.R
import go.goskate.goskate.helper.adapters.ViewPagerAdapter

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewInfo = inflater.inflate(R.layout.view_pager_fragment, container, false)
        val fragmentList = listOf<Fragment>(
            InfoSpot(),
            FilesSpot(),
            CommentSpots()
        )


        //   viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, tabLayout.tabCount)
        return viewInfo
    }
}