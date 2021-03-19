package go.goskate.goskate.data


import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import go.goskate.goskate.vo.PostVO
import java.util.*
import kotlin.collections.HashMap


class NewsRepository {


    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun savePost(postVO: PostVO): MutableLiveData<String> {
        postVO.userId = auth.currentUser!!.uid
        val result = MutableLiveData<String>()
        val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("News")
        val refStorage = storage.reference.child("newsFiles/" + UUID.randomUUID().toString())
        if (postVO.captureType == PostVO.TypeCapture.PHOTO) {
            refStorage.putFile(postVO.captureFile!!.toUri()).addOnSuccessListener {
                refStorage.downloadUrl.addOnSuccessListener {
                    postVO.captureFile = it.toString()
                    val userMap = HashMap<String, Any>()
                    userMap["captureFile"] = postVO.captureFile!!
                    userMap["captureType"] = postVO.captureType!!
                    userMap["title"] = postVO.title
                    userMap["userId"] = postVO.userId
                    userMap["description"] = postVO.description
                    userMap["typePost"] = postVO.typePost!!
                    userMap["profileImageUser"] = postVO.profileImageUser
                    userMap["nameUser"] = postVO.nameUser

                    val id = userRef.push().key
                    id?.let { it1 ->
                        userRef.child(it1).setValue(userMap)
                            .addOnCompleteListener { task ->
                                result.value = if (task.isSuccessful) {
                                    "Successful"
                                } else {
                                    task.exception?.toString()!!
                                }
                            }
                    }
                }

            }
        } else {
            refStorage.putFile(postVO.captureFile!!.toUri()).addOnSuccessListener {
                refStorage.downloadUrl.addOnSuccessListener {
                    postVO.captureFile = it.toString()
                    val userMap = HashMap<String, Any>()
                    userMap["captureFile"] = postVO.captureFile!!
                    userMap["captureType"] = postVO.captureType!!
                    userMap["title"] = postVO.title
                    userMap["userId"] = postVO.userId
                    userMap["description"] = postVO.description
                    userMap["typePost"] = postVO.typePost!!
                    userMap["profileImageUser"] = postVO.profileImageUser
                    userMap["nameUser"] = postVO.nameUser

                    val id = userRef.push().key
                    id?.let { it1 ->
                        userRef.child(it1).setValue(userMap)
                            .addOnCompleteListener { task ->
                                result.value = if (task.isSuccessful) {
                                    "Successful"
                                } else {
                                    task.exception?.toString()!!
                                }
                            }
                    }
                }

            }
        }

        return result
    }

    fun getAllPost(): MutableLiveData<List<PostVO>> {
        val resultPost = MutableLiveData<List<PostVO>>()
        val dataResult = mutableListOf<PostVO>()
        val userRef = FirebaseDatabase.getInstance().reference.child("News")
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (ds in snapshot.children) {
                        val data: GenericTypeIndicator<PostVO> =
                            object : GenericTypeIndicator<PostVO>() {}
                        val posts: PostVO = ds.getValue(data)!!
                        dataResult.add(posts)
                    }
                    resultPost.value = dataResult
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("data", error.message)
            }
        })
        userRef.keepSynced(true)
        return resultPost
    }


}

