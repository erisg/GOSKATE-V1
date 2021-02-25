package go.goskate.goskate.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import go.goskate.goskate.vo.PostVO
import go.goskate.goskate.vo.UserVO

class UserRepository {

    var firebaseDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var userId = FirebaseAuth.getInstance().currentUser!!.uid

    fun getUserInfo(): MutableLiveData<UserVO> {
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


    fun getAllPostForUser(): MutableLiveData<MutableList<PostVO>> {
        val resultPost = MutableLiveData<MutableList<PostVO>>()
        val userVO = mutableListOf<PostVO>()
        firebaseDataBase.reference.child("News")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (ds in snapshot.children) {
                            val t: GenericTypeIndicator<PostVO?> =
                                object : GenericTypeIndicator<PostVO?>() {}
                            val userGetValue: PostVO = ds.getValue(t)!!
                            if (userGetValue.userId == userId) {
                                userVO.add(userGetValue)
                            }
                        }
                        resultPost.value = userVO
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("USER", error.message)
                }
            })

        return resultPost
    }
}