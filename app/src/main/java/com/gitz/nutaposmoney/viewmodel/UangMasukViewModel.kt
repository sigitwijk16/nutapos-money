package com.gitz.nutaposmoney.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gitz.nutaposmoney.data.AppDatabase
import com.gitz.nutaposmoney.data.UangMasukEntity
import com.gitz.nutaposmoney.repository.UangMasukRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UangMasukViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UangMasukRepository
    val allUangMasuk: LiveData<List<UangMasukEntity>>

    init {
        val uangMasukDao = AppDatabase.getDatabase(application).uangMasukDao()
        repository = UangMasukRepository(uangMasukDao)
        allUangMasuk = repository.allUangMasuk
    }

    fun insert(uangMasuk: UangMasukEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(uangMasuk)
    }

    fun update(uangMasuk: UangMasukEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(uangMasuk)
    }

    fun delete(uangMasuk: UangMasukEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(uangMasuk)
    }
}
