package go.goskate.goskate.vo

class NewsVO {

    var title: String = ""
    var location: String = ""
    var image: String = ""

    constructor()

    constructor(title: String, location: String, image: String) {
        this.title = title
        this.location = location
        this.image = image
    }
}