package go.goskate.goskate.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import go.goskate.goskate.vo.UserVO

class AuthRepository() {

    fun saveUser(newUser: UserVO): MutableLiveData<String> {
        val mutableDataResponse = MutableLiveData<String>()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(newUser.userEmail, newUser.userEmail)
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
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.userEmail, user.userPassword)
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

}