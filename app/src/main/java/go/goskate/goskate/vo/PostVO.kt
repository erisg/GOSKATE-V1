package go.goskate.goskate.vo

import android.graphics.Bitmap
import android.net.Uri

class PostVO {

    var typeCapture: TypeCapture? = null
    var fileImageCapture: Bitmap? = null
    var fileVideoCapture: String? = null
    var likesNumbers: Int = 0
    var likes: String = ""
    var location: String = ""

    constructor()

    constructor(fileImageCapture: Bitmap) {
        this.fileImageCapture = fileImageCapture
    }

    constructor(
        typeCapture: TypeCapture,
        fileImageCapture: Bitmap,
        fileVideoCapture: String,
        likesNumbers: Int,
        likes: String,
        location: String
    ) {
        this.typeCapture = typeCapture
        this.fileImageCapture = fileImageCapture
        this.fileVideoCapture = fileVideoCapture
        this.likesNumbers = likesNumbers
        this.likes = likes
        this.location = location
    }


    enum class TypeCapture(val resource: String) {
        PHOTO("photo"),
        VIDEO("video")
    }

}

