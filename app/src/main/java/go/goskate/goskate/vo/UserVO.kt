package go.goskate.goskate.vo

class UserVO {
    var userId: String = ""
    var userName: String = ""
    var userEmail: String = ""
    var userPassword: String = ""
    var userTelephone: String = ""
    var ageUser: String = ""
    var userGender: Boolean? = null
    var imageProfile: String? = null

    constructor()

    constructor(userEmail: String, userPassword: String) {
        this.userEmail = userEmail
        this.userPassword = userPassword
    }

    constructor(userName: String, userEmail: String, userTelephone: String, profileImage: String) {
        this.userName = userName
        this.userEmail = userEmail
        this.userTelephone = userTelephone
        this.imageProfile = profileImage
    }


    constructor(
        userName: String,
        userEmail: String,
        userPassword: String,
        userTelephone: String,
        userAge: String,
        userGender: Boolean?
    ) {
        this.userName = userName
        this.userEmail = userEmail
        this.userPassword = userPassword
        this.userTelephone = userTelephone
        this.ageUser = userAge
        this.userGender = userGender
    }
}