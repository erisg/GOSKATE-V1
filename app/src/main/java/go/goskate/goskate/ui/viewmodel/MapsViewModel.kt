package go.goskate.goskate.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import go.goskate.goskate.data.MapRepository
import go.goskate.goskate.vo.FileCaptureVO
import go.goskate.goskate.vo.SpotVO

class MapsViewModel : ViewModel() {

    var spotVO = SpotVO()
    var imagesNewSpot = MutableLiveData<FileCaptureVO>()
    val mapRepository = MapRepository()

    fun setInfoSpot(): MutableLiveData<String> {
        return mapRepository.saveInfoSpot(spotVO)
    }
}