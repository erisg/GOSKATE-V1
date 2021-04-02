package go.goskate.goskate.data

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import go.goskate.goskate.vo.FileCaptureVO
import go.goskate.goskate.vo.SpotVO
import java.util.*

class MapRepository {


    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    var uriList = mutableListOf<FileCaptureVO>()

    fun saveInfoSpot(spotVO: SpotVO): MutableLiveData<String> {
        val resultPost = MutableLiveData<String>()
        val spotRef = FirebaseDatabase.getInstance().reference.child("Spots")
        val refStorage = storage.reference.child("SpotFiles/" + UUID.randomUUID().toString())
        spotVO.files?.forEach { fileCaptureVO ->
            refStorage.putFile(fileCaptureVO.fileSpot!!).addOnSuccessListener {
                if (it.task.isSuccessful) {
                    refStorage.downloadUrl.addOnSuccessListener {
                        uriList.add(FileCaptureVO(it, fileCaptureVO.typePost))
                    }
                } else {
                    resultPost.value = it.task.exception?.message
                }
            }

        }

        if (uriList.size > 0) {
            spotVO.files = uriList
            val userMap = HashMap<String, Any>()
            userMap["nameSpot"] = spotVO.nameSpot
            userMap["category"] = spotVO.category
            userMap["nameHood"] = spotVO.nameHood
            userMap["locality"] = spotVO.locality
            userMap["files"] = spotVO.files!!
            userMap["score"] = spotVO.score
            userMap["latitude"] = spotVO.latitude
            userMap["longitude"] = spotVO.longitude
            userMap["comments"] = spotVO.comments

            val id = spotRef.push().key
            id?.let { it1 ->
                spotRef.child(it1).setValue(userMap)
                    .addOnCompleteListener { task ->
                        resultPost.value = if (task.isSuccessful) {
                            "Successful"
                        } else {
                            task.exception?.toString()!!
                        }
                    }
            }
        } else {
            resultPost.value = "Ups! Algo Esta fallando intentalo mas tarde"
        }


        return resultPost
    }
}