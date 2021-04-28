package cz.tadeasvalenta.appliftingshowcaseapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "landpads")
data class Landpad(
    val name: String,
    @SerializedName("full_name") val fullName: String,
    val type: String,
    val locality: String,
    val region: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("landing_attempts") val landingAttempts: Int,
    @SerializedName("landing_successes") val landingSuccesses: Int,
    val wikipedia: String,
    val details: String,
    val launches: List<String>,
    val status: String,
    @PrimaryKey val id: String
)
