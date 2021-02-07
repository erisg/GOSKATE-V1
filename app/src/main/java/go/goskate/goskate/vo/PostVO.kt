package go.goskate.goskate.vo

import android.graphics.Bitmap
import android.net.Uri

class PostVO {

    var typeCapture: TypeCapture? = null
    var fileImageCapture: String? = null
    var fileVideoCapture: String? = null
    var likesNumbers: Int = 0
    var likes: String = ""
    var description: String = ""
    var location: String = ""

    constructor()

    constructor(fileImageCapture: String) {
        this.fileImageCapture = fileImageCapture
    }

    constructor(
        typeCapture: TypeCapture,
        fileImageCapture: String,
        location: String,
        description: String
    ) {
        this.typeCapture = typeCapture
        this.fileImageCapture = fileImageCapture
        this.location = location
        this.description = description
    }

    constructor(
        typeCapture: TypeCapture,
        fileImageCapture: String,
        fileVideoCapture: String?,
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

