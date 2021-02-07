package go.goskate.goskate.ui.viewmodel

import android.graphics.Bitmap
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import go.goskate.goskate.data.NewsRepository
import go.goskate.goskate.vo.NewsVO
import go.goskate.goskate.vo.PostVO

class NewsViewModel : ViewModel() {

    var newsPost = mutableListOf<NewsVO>()
    var postVO = PostVO()
    var newsRepository = NewsRepository()

    fun setInfoPost(): MutableLiveData<String> {
        return newsRepository.savePost(postVO)
    }

    fun getAllPost(): MutableLiveData<List<PostVO>> {
        return newsRepository.getAllPost()
    }
}