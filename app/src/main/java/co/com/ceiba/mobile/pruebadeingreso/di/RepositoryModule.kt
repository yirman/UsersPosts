package co.com.ceiba.mobile.pruebadeingreso.di


import co.com.ceiba.mobile.pruebadeingreso.data.local.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.remote.UserRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideUserRepository(remoteDataSource: UserRemoteDataSource, userDao: UserDao) =
        UserRepository(remoteDataSource, userDao)

}