package go.goskate.goskate.helper.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import go.goskate.goskate.ui.maps.CommentSpots
import go.goskate.goskate.ui.maps.FilesSpot
import go.goskate.goskate.ui.maps.InfoSpot


class ViewPagerAdapter(fm: FragmentManager, var totalTabs: Int) : FragmentStatePagerAdapter(fm) {


    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> {
            InfoSpot.newInstance()
        }
        1 -> {
            FilesSpot.newInstance()
        }
        2 -> {
            CommentSpots.newInstance()
        }
        else -> {
            InfoSpot.newInstance()
        }
    }


}