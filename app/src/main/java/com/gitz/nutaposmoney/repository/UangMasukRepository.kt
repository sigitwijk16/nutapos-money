package com.gitz.nutaposmoney.repository

import androidx.lifecycle.LiveData
import com.gitz.nutaposmoney.data.UangMasukDao
import com.gitz.nutaposmoney.data.UangMasukEntity

class UangMasukRepository(private val uangMasukDao: UangMasukDao) {
    val allUangMasuk: LiveData<List<UangMasukEntity>> = uangMasukDao.getAllUangMasuk()

    suspend fun insert(uangMasuk: UangMasukEntity) {
        uangMasukDao.insert(uangMasuk)
    }

    suspend fun update(uangMasuk: UangMasukEntity) {
        uangMasukDao.update(uangMasuk)
    }

    suspend fun delete(uangMasuk: UangMasukEntity) {
        uangMasukDao.delete(uangMasuk)
    }
}
