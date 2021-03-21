package go.goskate.goskate.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import go.goskate.goskate.data.NewsRepository
import go.goskate.goskate.data.UserRepository
import go.goskate.goskate.vo.PostVO
import go.goskate.goskate.vo.UserVO

class NewsViewModel : ViewModel() {

    var postVO = PostVO()
    var newsRepository = NewsRepository()
    private val userRepository = UserRepository()

    fun setInfoPost(): MutableLiveData<String> {
        return newsRepository.savePost(postVO)
    }

    fun getAllPost(): MutableLiveData<List<PostVO>> {
        return newsRepository.getAllPost()
    }

    fun getInfoUserProfile(): MutableLiveData<UserVO> {
        return userRepository.getUserInfo()
    }
}