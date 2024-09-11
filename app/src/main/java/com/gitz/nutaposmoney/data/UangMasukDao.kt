package com.gitz.nutaposmoney.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UangMasukDao {
    @Query("SELECT * FROM uang_masuk ORDER BY tanggal DESC")
    fun getAllUangMasuk(): LiveData<List<UangMasukEntity>>

    @Insert
    suspend fun insert(uangMasuk: UangMasukEntity)

    @Update
    suspend fun update(uangMasuk: UangMasukEntity)

    @Delete
    suspend fun delete(uangMasuk: UangMasukEntity)
}
