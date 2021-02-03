package go.goskate.goskate.data

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import go.goskate.goskate.vo.UserVO

class AuthRepository() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun saveUser(newUser: UserVO): MutableLiveData<String> {
        val mutableDataResponse = MutableLiveData<String>()
        auth.createUserWithEmailAndPassword(newUser.userEmail, newUser.userEmail)
            .addOnCompleteListener { task ->
                val message = task.exception.toString()
                mutableDataResponse.value = if (task.isSuccessful) {
                    "Successful"
                } else {
                    message
                }
            }
        return mutableDataResponse
    }

    fun isUserExist(user: UserVO): MutableLiveData<String> {
        val mutableDataLoginResponse = MutableLiveData<String>()
        auth.signInWithEmailAndPassword(user.userEmail, user.userPassword)
            .addOnCompleteListener { task ->
                val message = task.exception.toString()
                mutableDataLoginResponse.value = if (task.isSuccessful) {
                    "Successful"
                } else {
                    message
                }
            }
        return mutableDataLoginResponse
    }

    fun signInWithCredential(account: GoogleSignInAccount?): MutableLiveData<String> {
        val mutableDataLoginWithGoogleResponse = MutableLiveData<String>()
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            val message = task.exception.toString()
            mutableDataLoginWithGoogleResponse.value = if (task.isSuccessful) {
                "Successful"
            } else {
                message
            }
        }
        return mutableDataLoginWithGoogleResponse
    }


    fun recoverAccountWhitEmail(email: String): MutableLiveData<String> {
        val mutableRecoverAccountWhitEmail = MutableLiveData<String>()
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            val message = task.exception.toString()
            mutableRecoverAccountWhitEmail.value = if (task.isSuccessful) {
                "Por favor revisa tu correo para restablecer la contrase;a"
            } else {
                message
            }
        }

        return mutableRecoverAccountWhitEmail
    }

}