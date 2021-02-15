package go.goskate.goskate.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import go.goskate.goskate.data.MapRepository
import go.goskate.goskate.vo.SpotVO

class MapsViewModel : ViewModel() {

    var spotVO = SpotVO()
    var imagesNewSpot = MutableLiveData<Bitmap>()
    val mapRepository = MapRepository()

    fun setInfoSpot() {
        mapRepository.saveInfoSpot(spotVO)
    }
}