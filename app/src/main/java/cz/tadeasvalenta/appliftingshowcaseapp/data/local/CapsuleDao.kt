package cz.tadeasvalenta.appliftingshowcaseapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Capsule

@Dao
interface CapsuleDao {

    @Query("SELECT * FROM capsules")
    fun getAllCapsules(): LiveData<List<Capsule>>

    @Query("SELECT * FROM capsules WHERE id = :id")
    fun getCapsule(id: String): LiveData<Capsule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(capsules: List<Capsule>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(capsule: Capsule)
}
