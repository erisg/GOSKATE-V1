package go.goskate.goskate.ui.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import go.goskate.goskate.data.AuthRepository
import go.goskate.goskate.vo.UserVO

class AuthViewModel : ViewModel() {

    val userVO = UserVO()
    var profileImage = MutableLiveData<Bitmap>()
    private val authRepository = AuthRepository()

    fun dataNewUser(): MutableLiveData<String> {
        return authRepository.saveUser(userVO)
    }

    fun dataLogin(email: String, password: String): MutableLiveData<String> {
        this.userVO.userEmail = email
        this.userVO.userPassword = password
        return authRepository.isUserExist(userVO)
    }

    fun dataLoginWithCredentials(account: GoogleSignInAccount?): MutableLiveData<String> {
        return authRepository.signInWithCredential(account)
    }

    fun dataRecoverAccount(email: String): MutableLiveData<String> {
        return authRepository.recoverAccountWhitEmail(email)
    }
}