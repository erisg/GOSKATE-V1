package go.goskate.goskate.helper.dialogs

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.MapsViewModel
import kotlinx.android.synthetic.main.new_spot_map.*
import kotlinx.android.synthetic.main.new_spot_map.view.*


class NewSpotMapDialogFragment : DialogFragment(), OnMapReadyCallback,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnCameraMoveStartedListener {


    private val mapsViewModel: MapsViewModel by activityViewModels()

    private var mMap: GoogleMap? = null
    private var mapView: MapView? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.new_spot_map, container)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())


        mapView = rootView.findViewById(R.id.mapView) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.onResume()
        mapView!!.getMapAsync(this)

        rootView.saveButton.setOnClickListener {

        }
        return rootView
    }


    private fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                requireContext(),
                "SE DEBEN ACEPTAR LOS PERMISOS PARA MOSTRAR EL MAPA",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    mapsViewModel.spotVO.longitude = location.longitude
                    mapsViewModel.spotVO.latitude = location.latitude

                    val cameraPlace =
                        LatLng(mapsViewModel.spotVO.latitude, mapsViewModel.spotVO.longitude)
                    mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraPlace, 19f))
                }

            }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        getLastKnownLocation()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap.let {
            mMap = it!!
        }
        mMap?.setOnCameraIdleListener(this)
        mMap?.setOnCameraMoveStartedListener(this)
        mMap?.uiSettings?.isMyLocationButtonEnabled = true
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(requireContext(), "", Toast.LENGTH_LONG).show()
            return
        }
        mMap?.isMyLocationEnabled = true

        try {
            val success = mMap?.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(this.context, R.raw.mapstyle)
            )
            if (!success!!) {
                Log.e("oo", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("oo", "Can't find style. Error: ", e)
        }

    }

    override fun onCameraIdle() {
        mapsViewModel.spotVO.latitude = mMap?.cameraPosition?.target?.latitude!!
        mapsViewModel.spotVO.longitude = mMap?.cameraPosition?.target?.longitude!!

    }

    override fun onCameraMoveStarted(reason: Int) {
        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            locationImageView.animate().translationY(-((locationImageView.height / 2)).toFloat())
        }
    }


}