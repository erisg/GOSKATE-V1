package go.goskate.goskate.ui.viewmodel

import androidx.lifecycle.ViewModel
import go.goskate.goskate.vo.PostVO

class UserProfileViewModel : ViewModel() {

    var postVO = PostVO()
    var isAdded = false
}