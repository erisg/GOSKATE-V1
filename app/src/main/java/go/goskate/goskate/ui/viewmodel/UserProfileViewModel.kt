package go.goskate.goskate.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import go.goskate.goskate.data.UserRepository
import go.goskate.goskate.vo.PostVO
import go.goskate.goskate.vo.UserVO

class UserProfileViewModel : ViewModel() {


    var imagesPost = MutableLiveData<String>()
    private val userRepository = UserRepository()

    fun getInfoUserProfile(): UserVO {
        return userRepository.getUserInfo()
    }

    fun getAllPostForUser(): MutableLiveData<MutableList<PostVO>> {
        return userRepository.getAllPostForUser()
    }

}