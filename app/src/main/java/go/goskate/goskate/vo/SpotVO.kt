package go.goskate.goskate.vo

import java.io.File

class SpotVO {

    var nameSpot = ""
    var category = ""
    var nameHood = ""
    var locality = ""
    var hourType = ""
    var hour = ""
    var files: List<File>? = null
    var score = 0
    var latitude = 0F
    var longitude = 0F
    var comments = ""

    constructor()

    constructor(
        nameSpot: String,
        category: String,
        nameHood: String,
        locality: String,
        hourType: String,
        hour: String,
        files: List<File>,
        score: Int,
        latitude: Float,
        longitude: Float,
        comments: String
    ) {
        this.nameSpot = nameSpot
        this.category = category
        this.nameHood = nameHood
        this.locality = locality
        this.hourType = hourType
        this.hour = hour
        this.files = files
        this.score = score
        this.latitude = latitude
        this.longitude = longitude
        this.comments = comments

    }
}