package go.goskate.goskate.vo

import android.net.Uri

class FileCaptureVO {

    var fileSpot: Uri? = null
    var typePost: String = ""

    constructor()

    constructor(fileSpot: Uri)

    constructor(fileSpot: Uri?, typePost: String) {
        this.typePost = typePost
        this.fileSpot = fileSpot
    }

}