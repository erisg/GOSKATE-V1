package go.goskate.goskate.vo

import android.net.Uri
import java.io.File

class SpotVO {

    var nameSpot = ""
    var category = ""
    var nameHood = ""
    var locality = ""
    var hourType = ""
    var hour = ""
    var files: MutableList<FileCaptureVO>? = null
    var score = 0
    var latitude = 0.0
    var longitude = 0.0
    var comments = ""

    constructor()

    constructor(
        nameSpot: String,
        category: String,
        nameHood: String,
        locality: String,
        hourType: String,
        hour: String,
        files: MutableList<FileCaptureVO>,
        score: Int,
        latitude: Double,
        longitude: Double,
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