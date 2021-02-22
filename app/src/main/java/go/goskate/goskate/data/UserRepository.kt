package go.goskate.goskate.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import go.goskate.goskate.vo.PostVO
import go.goskate.goskate.vo.UserVO

class UserRepository {

    var firebaseDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getUserInfo(userId: String): MutableLiveData<UserVO> {
        val userVO = MutableLiveData<UserVO>()
        firebaseDataBase.reference.child("Users").child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val t: GenericTypeIndicator<UserVO?> =
                            object : GenericTypeIndicator<UserVO?>() {}
                        val userGetValue: UserVO = snapshot.getValue(t)!!
                        userVO.value = userGetValue
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("USER", error.message)
                }
            })

        return userVO
    }
}