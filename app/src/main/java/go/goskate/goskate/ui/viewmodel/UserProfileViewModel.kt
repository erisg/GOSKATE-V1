package go.goskate.goskate.ui.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import go.goskate.goskate.vo.PostVO

class UserProfileViewModel : ViewModel() {

    var postVO = PostVO()
    var imagesPost = MutableLiveData<Bitmap>()
    var isAdded = false
}