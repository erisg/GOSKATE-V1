package go.goskate.goskate.ui.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import go.goskate.goskate.data.AuthRepository
import go.goskate.goskate.vo.UserVO

class AuthViewModel : ViewModel() {

    val userVO = UserVO()
    var profileImage = MutableLiveData<Uri>()
    val authRepository = AuthRepository()

    fun dataNewUser(): MutableLiveData<String> {
        return authRepository.saveUser(userVO)
    }

    fun dataLogin() {

    }
}