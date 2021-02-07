package go.goskate.goskate.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import go.goskate.goskate.vo.UserVO

class UserRepository {

    var firebaseDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getUserInfo(userId: FirebaseUser): UserVO {
        val userVO = UserVO()
        val userInfo = firebaseDataBase.reference.orderByChild("userEmail").equalTo(userId.email)
        userInfo.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val idFromDB = snapshot.child(userId.uid).child("uid").value.toString()
                if (snapshot.exists()) {
                    userVO.profileImage = snapshot.child(idFromDB).child("uid").value.toString()
                    userVO.userName = snapshot.child(idFromDB).child("userName").value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return userVO
    }
}