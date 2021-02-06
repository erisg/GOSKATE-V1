package go.goskate.goskate.ui.viewmodel

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import go.goskate.goskate.vo.NewsVO
import go.goskate.goskate.vo.PostVO

class NewsViewModel : ViewModel() {

    var newsPost = mutableListOf<NewsVO>()
    var postVO = PostVO()

    fun setInfoPost() {

    }
}