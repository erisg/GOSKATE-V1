package go.goskate.goskate.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import go.goskate.goskate.R
import go.goskate.goskate.helper.adapters.fileAdapter
import kotlinx.android.synthetic.main.files_spot.*

class FilesSpot : Fragment() {

    companion object {
        fun newInstance(): FilesSpot = FilesSpot()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.files_spot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lista = listOf<String>(
            "fmyhjhk",
            "gkfuyi",
            "ftufuyf",
            "guitguj ",
            "fmyhjhk",
            "gkfuyi",
            "ftufuyf",
            "guitguj",
            "guitguj ",
            "fmyhjhk",
            "gkfuyi",
            "ftufuyf",
            "guitguj "
        )


        filesSpotRecyclerView.adapter = fileAdapter(this.requireContext(), lista)

    }
}