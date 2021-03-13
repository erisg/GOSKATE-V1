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
    var title: String = ""

    constructor()

    constructor(fileImageCapture: String) {
        this.fileCapture = fileCapture
    }

    constructor(
        typeCapture: TypeCapture,
        fileImageCapture: String,
        title: String,
        userId: String,
        description: String
    ) {
        this.typeCapture = typeCapture
        this.fileCapture = fileCapture
        this.title = title
        this.userId = userId
        this.description = description
    }

    constructor(
        typeCapture: TypeCapture,
        fileImageCapture: String,
        fileVideoCapture: String?,
        likesNumbers: Int,
        likes: String,
        title: String
    ) {
        this.typeCapture = typeCapture
        this.fileCapture = fileCapture
        this.likesNumbers = likesNumbers
        this.likes = likes
        this.title = title
    }


    enum class TypeCapture(val resource: String) {
        PHOTO("photo"),
        VIDEO("video")
    }

}

