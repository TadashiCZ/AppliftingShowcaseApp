package cz.tadeasvalenta.appliftingshowcaseapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "capsules")
data class Capsule(
    @SerializedName("reuse_count") val reuseCount: Int,
    @SerializedName("water_landings") val waterLandings: Int,
    @SerializedName("land_landings") val landLandings: Int,
    @SerializedName("last_update") val lastUpdate: String,
    val launches: List<String>,
    val serial: String,
    val status: String,
    val type: String,
    @PrimaryKey val id: String
)
