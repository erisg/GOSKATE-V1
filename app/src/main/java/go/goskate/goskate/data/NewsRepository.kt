package go.goskate.goskate.data


import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import go.goskate.goskate.vo.PostVO
import java.util.*
import kotlin.collections.HashMap


class NewsRepository {


    var storage: FirebaseStorage = FirebaseStorage.getInstance()

    fun savePost(postVO: PostVO): MutableLiveData<String> {
        val result = MutableLiveData<String>()
        val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("News")
        val refStorage = storage.reference.child("imagesNews/" + UUID.randomUUID().toString())
        val fileName = refStorage.child("img" + postVO.fileImageCapture)
        fileName.putFile(postVO.fileImageCapture!!.toUri()).addOnSuccessListener {
            fileName.downloadUrl.addOnSuccessListener {
                postVO.fileImageCapture = it.toString()
                val userMap = HashMap<String, Any>()
                userMap["fileImageCapture"] = postVO.fileImageCapture!!
                userMap["typeCapture"] = postVO.typeCapture!!
                userMap["location"] = postVO.location
                userMap["description"] = postVO.description

                userRef.setValue(userMap)
                    .addOnCompleteListener { task ->
                        result.value = if (task.isSuccessful) {
                            "Successful"
                        } else {
                            task.exception?.toString()!!
                        }
                    }
            }

        }

        return result
    }

    fun getAllPost(): MutableLiveData<List<PostVO>> {
        val resultPost = MutableLiveData<List<PostVO>>()
        val postVO = PostVO()
        val data = HashMap<String, PostVO>()
        val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("News")
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val t: GenericTypeIndicator<PostVO?> = object : GenericTypeIndicator<PostVO?>() {}
                val estudiante: PostVO = snapshot.getValue(t)!!
                resultPost.value = mutableListOf(estudiante)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return resultPost
    }


}

