package co.com.ceiba.mobile.pruebadeingreso.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.lang.Exception

@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<ResultType, RequestType> {

    fun asFlow() = flow {
        val data = fetchFromLocal().first()
        try{
            if(shouldFetchFromRemote(data)){
                emit(Resource.loading())
                val apiResponse = fetchFromRemote()
                saveRemoteData(apiResponse.data!!)
            }
        }catch (e: Exception){
            e.printStackTrace()
            emit(Resource.error("Network error!"))
        }
        emitAll(fetchFromLocal().map { Resource.success(it)})
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.error("Network error!"))
    }

    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: RequestType)

    @MainThread
    protected abstract fun fetchFromLocal(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Resource<RequestType>

    @MainThread
    protected abstract suspend fun shouldFetchFromRemote(data: ResultType): Boolean
}