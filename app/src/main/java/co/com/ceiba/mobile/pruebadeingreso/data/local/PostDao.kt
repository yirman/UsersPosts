package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.room.*
import co.com.ceiba.mobile.pruebadeingreso.data.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Post>)

    @Query("SELECT * FROM posts WHERE userId = :userId")
    fun queryAllUserPosts(userId: Int) : Flow<List<Post>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun queryPostsUser(userId: Int) : Flow<User>

}