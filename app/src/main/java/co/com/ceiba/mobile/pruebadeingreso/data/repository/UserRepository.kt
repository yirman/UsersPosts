package co.com.ceiba.mobile.pruebadeingreso.data.repository

import co.com.ceiba.mobile.pruebadeingreso.data.entities.User
import co.com.ceiba.mobile.pruebadeingreso.data.local.UserDao
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import co.com.ceiba.mobile.pruebadeingreso.data.remote.UserRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.utils.NetworkBoundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val userDao: UserDao
){

    fun getAllUsers(): Flow<Resource<List<User>>>{
        return object : NetworkBoundRepository<List<User>, List<User>>() {

            override suspend fun saveRemoteData(response: List<User>) = userDao.insertUsers(response)

            override fun fetchFromLocal(): Flow<List<User>> = userDao.queryAllUsers()

            override suspend fun fetchFromRemote(): Resource<List<User>> = remoteDataSource.requestUsers()

            override suspend fun shouldFetchFromRemote(data: List<User>): Boolean = data.isEmpty()

        }.asFlow().flowOn(Dispatchers.IO)
    }

    suspend fun searchUsers(search: String): Flow<Resource<List<User>>> = flow {
        emit(userDao.searchUsers(search).let { Resource.success(it) })
    }.flowOn(Dispatchers.IO)
}