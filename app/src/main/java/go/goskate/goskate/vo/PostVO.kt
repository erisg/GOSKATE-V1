package go.goskate.goskate.vo

import android.graphics.Bitmap
import android.net.Uri

class PostVO {

    var typeCapture: TypeCapture? = null
    var fileCapture: String? = null
    var userId: String = ""
    var likesNumbers: Int = 0
    var likes: String = ""
    var description: String = ""
    var location: String = ""

    constructor()

    constructor(fileImageCapture: String) {
        this.fileCapture = fileCapture
    }

    constructor(
        typeCapture: TypeCapture,
        fileImageCapture: String,
        location: String,
        userId: String,
        description: String
    ) {
        this.typeCapture = typeCapture
        this.fileCapture = fileCapture
        this.location = location
        this.userId = userId
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
        this.fileCapture = fileCapture
        this.likesNumbers = likesNumbers
        this.likes = likes
        this.location = location
    }


    enum class TypeCapture(val resource: String) {
        PHOTO("photo"),
        VIDEO("video")
    }

}

