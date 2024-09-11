package com.gitz.nutaposmoney.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "uang_masuk")
data class UangMasukEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tanggal: Long,
    val jam: String,
    val masukKe: String,
    val terimaDari: String,
    val keterangan: String,
    val jumlah: Double,
    val jenis: String,
    val fotoPath: String?
)
