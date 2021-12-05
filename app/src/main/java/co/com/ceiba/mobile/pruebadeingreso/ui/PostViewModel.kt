package co.com.ceiba.mobile.pruebadeingreso.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.ceiba.mobile.pruebadeingreso.data.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.data.entities.User
import co.com.ceiba.mobile.pruebadeingreso.data.repository.PostRepository
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

@ExperimentalCoroutinesApi
@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel(){

    private val _postList = MutableLiveData<Resource<List<Post>>>()
    val postList = _postList

    private val _user = MutableLiveData<User>()
    val postsUser: LiveData<User> = _user

    fun queryPostsUser(userId: Int){
        viewModelScope.launch {
            repository.queryPostsUser(userId).collect{ _user.value = it.data!! }
        }
    }

    fun getPosts(userId: Int){
        viewModelScope.launch{
            repository.getPosts(userId).collect{
                _postList.value = it
            }
        }
    }

}