package com.gitz.nutaposmoney.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gitz.nutaposmoney.data.UangMasukEntity
import com.gitz.nutaposmoney.databinding.ItemUangMasukBinding
import java.text.SimpleDateFormat
import java.util.*

class UangMasukAdapter : ListAdapter<UangMasukEntity, UangMasukAdapter.ViewHolder>(UangMasukDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUangMasukBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemUangMasukBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(uangMasuk: UangMasukEntity) {
            binding.textJam.text = uangMasuk.jam
            binding.textMasukKe.text = uangMasuk.masukKe
            binding.textTerimaDari.text = uangMasuk.terimaDari
            binding.textKeterangan.text = uangMasuk.keterangan
            binding.textJumlah.text = String.format(Locale.getDefault(), "%.2f", uangMasuk.jumlah)

            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            binding.textTanggal.text = dateFormat.format(uangMasuk.tanggal)
        }
    }

    class UangMasukDiffCallback : DiffUtil.ItemCallback<UangMasukEntity>() {
        override fun areItemsTheSame(oldItem: UangMasukEntity, newItem: UangMasukEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UangMasukEntity, newItem: UangMasukEntity): Boolean {
            return oldItem == newItem
        }
    }
}