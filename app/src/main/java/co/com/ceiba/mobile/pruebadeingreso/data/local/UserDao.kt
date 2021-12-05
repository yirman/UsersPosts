package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.room.*
import co.com.ceiba.mobile.pruebadeingreso.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("SELECT * FROM users")
    fun queryAllUsers() : Flow<List<User>>

    @Query("SELECT * FROM users WHERE name LIKE '%' || :query || '%'")
    suspend fun searchUsers(query :String) : List<User>

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}