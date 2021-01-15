package go.goskate.goskate.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.MapsViewModel

class Maps : Fragment(), OnMapReadyCallback {

    companion object {
        fun newInstance() = Maps()
    }

    private lateinit var viewModel: MapsViewModel
    var supportFragmentManager = SupportMapFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.maps_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        supportFragmentManager.childFragmentManager.findFragmentById(R.id.mapFrameLayout)
        supportFragmentManager.getMapAsync(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onMapReady(p0: GoogleMap?) {
        TODO("Not yet implemented")
    }

}