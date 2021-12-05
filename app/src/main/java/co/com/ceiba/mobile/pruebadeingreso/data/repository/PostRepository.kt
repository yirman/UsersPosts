package co.com.ceiba.mobile.pruebadeingreso.data.repository

import co.com.ceiba.mobile.pruebadeingreso.data.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.data.entities.User
import co.com.ceiba.mobile.pruebadeingreso.data.local.PostDao
import co.com.ceiba.mobile.pruebadeingreso.data.remote.PostRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.utils.NetworkBoundRepository
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PostRepository @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val postDao: PostDao
){

    fun getPosts(userId: Int): Flow<Resource<List<Post>>>{
        return object : NetworkBoundRepository<List<Post>, List<Post>>() {

            override suspend fun saveRemoteData(response: List<Post>) = postDao.insertPosts(response)

            override fun fetchFromLocal(): Flow<List<Post>> = postDao.queryAllUserPosts(userId)

            override suspend fun fetchFromRemote(): Resource<List<Post>> = remoteDataSource.requestPosts(userId)

            override suspend fun shouldFetchFromRemote(data: List<Post>): Boolean = true

        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun queryPostsUser(userId: Int) = postDao.queryPostsUser(userId).map { Resource.success(it) }.flowOn(Dispatchers.IO)

}