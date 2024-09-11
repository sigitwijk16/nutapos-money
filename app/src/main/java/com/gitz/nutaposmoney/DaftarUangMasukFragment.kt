package com.gitz.nutaposmoney
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gitz.nutaposmoney.data.UangMasukEntity
import com.gitz.nutaposmoney.databinding.FragmentDaftarUangMasukBinding
import com.gitz.nutaposmoney.ui.UangMasukAdapter
import com.gitz.nutaposmoney.viewmodel.UangMasukViewModel
import java.text.SimpleDateFormat
import java.util.*

class DaftarUangMasukFragment : Fragment() {

    private var _binding: FragmentDaftarUangMasukBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UangMasukViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaftarUangMasukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UangMasukViewModel::class.java)

        val adapter = UangMasukAdapter()
        binding.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding.recyclerView?.adapter = adapter

        viewModel.allUangMasuk.observe(viewLifecycleOwner) { uangMasukList ->
            adapter.submitList(uangMasukList)
            updateTotal(uangMasukList)
        }

        binding.buttonBuatTransaksi.setOnClickListener {
            // Navigate to InputUangMasukFragment
        }

        setupDatePicker()
    }

    private fun updateTotal(uangMasukList: List<UangMasukEntity>) {
        val total = uangMasukList.sumOf { it.jumlah }
        binding.textTotal.text = getString(R.string.total_format, total)
    }

    private fun setupDatePicker() {
        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        // Convert String to Editable
        binding.datePicker.text = Editable.Factory.getInstance().newEditable(dateFormat.format(Date()))

        binding.datePicker.setOnClickListener {
            // Show date picker dialog
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}