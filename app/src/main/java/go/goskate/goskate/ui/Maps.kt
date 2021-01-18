package go.goskate.goskate.ui

import android.content.res.Resources
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.MapsViewModel
import kotlinx.android.synthetic.main.maps_fragment.*

class Maps : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private var mapView: MapView? = null
    private var navController: NavController? = null
    private lateinit var viewModel: MapsViewModel

    companion object {
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.maps_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)
        navController = Navigation.findNavController(view)


        mapView = view.findViewById(R.id.mapFrameLayout) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.onResume()
        mapView!!.getMapAsync(this)

        floatingActionButton.setOnClickListener {
            navController!!.navigate(R.id.action_maps_to_newSpot2)
        }

    }


    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        mMap.setOnMarkerClickListener(this)


        val place = LatLng(4.648671, -74.120623)
        val zoomLevel = 11.8f
        mMap.addMarker(MarkerOptions().position(place).title("loveu"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, zoomLevel))
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        TODO("Not yet implemented")
    }

}