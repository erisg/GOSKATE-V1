package go.goskate.goskate.helper.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private final var fragmentList1: ArrayList<Fragment> = ArrayList()
    private final var fragmentTitleList1: ArrayList<String> = ArrayList()

    // this function adds the fragment and title in 2 separate  arraylist.
    fun addFragment(fragment: Fragment, title: String) {
        fragmentList1.add(fragment)
        fragmentTitleList1.add(title)
    }

    override fun getCount(): Int {
        return fragmentList1.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList1[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList1[position]
    }


}