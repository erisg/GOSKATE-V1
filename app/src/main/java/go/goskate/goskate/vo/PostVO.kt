package go.goskate.goskate.vo

class PostVO {

    var captureType: TypeCapture? = null
    var captureFile: String? = null
    var typePost: TypePost? = null
    var userId: String = ""
    var likesNumbers: Int = 0
    var likes: String = ""
    var description: String = ""
    var title: String = ""
    var profileImageUser: String = ""
    var nameUser: String = ""

    constructor()

    constructor(fileImageCapture: String) {
        this.captureFile = captureFile
    }

    constructor(
        typeCapture: TypeCapture,
        fileImageCapture: String,
        title: String,
        userId: String,
        description: String
    ) {
        this.captureType = typeCapture
        this.captureFile = captureFile
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
        title: String,
        typePost: TypePost,
        profileImageUser: String,
        nameUser: String

    ) {
        this.captureType = typeCapture
        this.captureFile = captureFile
        this.likesNumbers = likesNumbers
        this.likes = likes
        this.title = title
        this.typePost = typePost
        this.profileImageUser = profileImageUser
        this.nameUser = nameUser
    }


    enum class TypeCapture(val resource: String) {
        PHOTO("photo"),
        VIDEO("video")
    }

    enum class TypePost() {
        POST,
        NEWS
    }

}

