package co.com.ceiba.mobile.pruebadeingreso.ui

import androidx.lifecycle.*
import co.com.ceiba.mobile.pruebadeingreso.data.entities.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import co.com.ceiba.mobile.pruebadeingreso.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(){

    private var searchUsersJob: Job? = null

    private val _users = MutableLiveData<Resource<List<User>>>()

    val users: LiveData<Resource<List<User>>> = _users.also { getUsers() }

    private fun getUsers() {
        viewModelScope.launch {
            repository.getAllUsers().collect { resource -> _users.value = resource }
        }
    }

    fun searchUsers(searchInput: String){
        searchUsersJob?.cancel()
        searchUsersJob = viewModelScope.launch {
            repository.searchUsers(searchInput).collect {
                _users.value = it
            }
        }
    }
}