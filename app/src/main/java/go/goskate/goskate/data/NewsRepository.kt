package go.goskate.goskate.data


import android.util.Log
import android.widget.Toast
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
        val refStorage = storage.reference.child("filesNews/" + UUID.randomUUID().toString())
        if (postVO.typeCapture == PostVO.TypeCapture.PHOTO) {
            val fileName = refStorage.child("img" + postVO.fileCapture)
            fileName.putFile(postVO.fileCapture!!.toUri()).addOnSuccessListener {
                fileName.downloadUrl.addOnSuccessListener {
                    postVO.fileCapture = it.toString()
                    val userMap = HashMap<String, Any>()
                    userMap["fileCapture"] = postVO.fileCapture!!
                    userMap["typeCapture"] = postVO.typeCapture!!
                    userMap["location"] = postVO.location
                    userMap["userId"] = postVO.userId
                    userMap["description"] = postVO.description

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
            val fileName = refStorage.child("video" + postVO.fileCapture)
            fileName.putFile(postVO.fileCapture!!.toUri()).addOnSuccessListener {
                fileName.downloadUrl.addOnSuccessListener {
                    postVO.fileCapture = it.toString()
                    val userMap = HashMap<String, Any>()
                    userMap["fileCapture"] = postVO.fileCapture!!
                    userMap["typeCapture"] = postVO.typeCapture!!
                    userMap["location"] = postVO.location
                    userMap["userId"] = postVO.userId
                    userMap["description"] = postVO.description

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

