package go.goskate.goskate.ui.viewmodel

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import go.goskate.goskate.vo.NewsVO

class NewsViewModel : ViewModel() {

    var newsPost = mutableListOf<NewsVO>()
}