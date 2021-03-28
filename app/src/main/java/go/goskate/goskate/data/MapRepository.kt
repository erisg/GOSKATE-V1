package go.goskate.goskate.data

import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import go.goskate.goskate.vo.SpotVO
import java.util.*

class MapRepository {


    val spotRef = FirebaseDatabase.getInstance().reference.child("Spot")
    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    val refStorage = storage.reference.child("SpotFiles/" + UUID.randomUUID().toString())

    fun saveInfoSpot(spotVO: SpotVO) : MutableLiveData<List<SpotVO>> {
        val resultPost = MutableLiveData<List<SpotVO>>()
        spotVO.files?.forEach {uri->
            refStorage.putFile(uri).addOnSuccessListener {
                refStorage.downloadUrl.addOnSuccessListener {

                }
            }
        }

        return resultPost
    }
}