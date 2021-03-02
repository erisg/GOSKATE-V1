package go.goskate.goskate.customizedviews

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import go.goskate.goskate.R
import java.net.CacheRequest


class NewPostPopUp : DialogFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    LocationListener {

    private lateinit var mMap: GoogleMap
    private var mapView: MapView? = null
    private lateinit var currentPositionMarker: Marker
    val REQUEST_PERMISION_CODE = 100

    // Location

    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.new_spot_map, container)

        permission()

        mapView = rootView.findViewById(R.id.mapView) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.onResume()
        mapView!!.getMapAsync(this)

        return rootView
    }

    private fun intiLocation() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.fastestInterval = 300
        locationRequest.interval = 5000
        locationRequest.smallestDisplacement = 10f

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)

                val myLocation =
                    LatLng(location.lastLocation.latitude, location.lastLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 18f))
            }
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        // fusedLocationProviderClient.removeLocationUpdates(locationRequest,locationCallback, Looper.myLooper())


    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap.let {
            mMap = it!!
        }
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        mMap.isMyLocationEnabled = true



        try {
            val success = mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(this.context, R.raw.mapstyle)
            )
            if (!success) {
                Log.e("oo", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("oo", "Can't find style. Error: ", e)
        }

        // mMap.addMarker(MarkerOptions().position(lating).title("loveu"))

    }


    override fun onMarkerClick(marker: Marker?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLocationChanged(location: Location) {

        val latLng = LatLng(location.latitude, location.longitude)
        val cameraPosition = CameraPosition.Builder().target(latLng).zoom(14f).build()

        // mMap.clear(); // Call if You need To Clear Map

        // mMap.clear(); // Call if You need To Clear Map
        if (currentPositionMarker == null) currentPositionMarker = mMap.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                .position(latLng)
                .zIndex(20f)
        ) else currentPositionMarker.position = latLng


        mMap.animateCamera(
            CameraUpdateFactory
                .newCameraPosition(cameraPosition)
        )
    }

    private fun permission(): MutableLiveData<Boolean> {
        val permissionResponse = MutableLiveData<Boolean>()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            permissionResponse.value = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISION_CODE
            )
        }
        return permissionResponse
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (REQUEST_PERMISION_CODE == requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "permiso concedido", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(requireContext(), "permiso denegado", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        //   fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onDestroy()
    }

}