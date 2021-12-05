package co.com.ceiba.mobile.pruebadeingreso.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "users")
data class User (
	@PrimaryKey
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("email") val email : String,
	@SerializedName("phone") val phone : String,
)