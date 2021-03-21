package go.goskate.goskate.data

import android.util.Log
import com.google.firebase.database.*
import go.goskate.goskate.vo.PostVO

class Firebase {

    suspend fun getAllPost(): List<PostVO> {
        var dataResult = listOf<PostVO>()
        val userRef = FirebaseDatabase.getInstance().reference.child("News")
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (ds in snapshot.children) {
                        val data: GenericTypeIndicator<PostVO> =
                            object : GenericTypeIndicator<PostVO>() {}
                        val posts: PostVO = ds.getValue(data)!!
                        dataResult = listOf(posts)
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("data", error.message)
            }
        })
        userRef.keepSynced(true)
        return dataResult
    }
}