package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.data.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.utils.RemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    var postService: PostService
): RemoteDataSource(){

    suspend fun requestPosts(userId: Int): Resource<List<Post>> = getResult { postService.requestPosts(userId) }

}