package cz.tadeasvalenta.appliftingshowcaseapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Landpad

@Dao
interface LandpadDao {

    @Query("SELECT * FROM landpads")
    fun getAllLandpads(): LiveData<List<Landpad>>

    @Query("SELECT * FROM landpads WHERE id = :id")
    fun getLandpad(id: String): LiveData<Landpad>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(landpads: List<Landpad>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(landpad: Landpad)
}
