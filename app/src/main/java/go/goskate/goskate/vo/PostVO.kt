package go.goskate.goskate.vo

class PostVO {

    var typeCapture: TypeCapture? = null
    var fileCapture: String = ""
    var likesNumbers: Int = 0
    var likes: String = ""
    var location: String = ""

    constructor()

    constructor(
        typeCapture: TypeCapture,
        fileCapture: String,
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

