package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.data.entities.User
import co.com.ceiba.mobile.pruebadeingreso.utils.RemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    var userService: UserService
): RemoteDataSource(){

    suspend fun requestUsers(): Resource<List<User>> = getResult { userService.requestUsers() }

}