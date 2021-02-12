package go.goskate.goskate.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapsViewModel : ViewModel() {

    var imagesNewSpot = MutableLiveData<Bitmap>()
}