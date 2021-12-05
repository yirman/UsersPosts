package co.com.ceiba.mobile.pruebadeingreso.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts",
	foreignKeys = [ForeignKey(
		entity = User::class,
		parentColumns = arrayOf("id"),
		childColumns = arrayOf("userId"),
		onDelete = ForeignKey.CASCADE)
	],
	indices = [Index("userId")]
)
data class Post (
	@SerializedName("userId") val userId : Int,
	@PrimaryKey @SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("body") val body : String,
)