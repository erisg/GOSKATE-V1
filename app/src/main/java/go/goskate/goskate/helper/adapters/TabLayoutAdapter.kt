package go.goskate.goskate.helper.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabLayoutAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        TODO("Not yet implemented")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "INFORMACION"
            1 -> "FOTOS-VIDEOS"
            else -> {
                return "COMENTARIOS"
            }
        }
    }
}