package com.gitz.nutaposmoney
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gitz.nutaposmoney.data.UangMasukEntity
import com.gitz.nutaposmoney.databinding.FragmentInputUangMasukBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gitz.nutaposmoney.viewmodel.UangMasukViewModel
import java.text.SimpleDateFormat
import java.util.*

class InputUangMasukFragment : Fragment() {

    private var _binding: FragmentInputUangMasukBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UangMasukViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputUangMasukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UangMasukViewModel::class.java)

        setupToolbar()
        setupJenisDropdown()
        setupLebihTahuButton()
        setupFotoButton()
        setupSimpanButton()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupJenisDropdown() {
        val items = listOf("Pendapatan Lain", "Non Pendapatan")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.dropdownJenis as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun setupLebihTahuButton() {
        binding.buttonLebihTahu.setOnClickListener {
            showLebihTahuBottomSheet()
        }
    }

    private fun showLebihTahuBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_lebih_tahu, null)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    private fun setupSimpanButton() {
        binding.fabSimpan.setOnClickListener {
            saveUangMasuk()
        }
    }

    private fun saveUangMasuk() {
        val masukKe = binding.editMasukKe.text.toString()
        val dari = binding.editDari.text.toString()
        val jumlah = binding.editJumlah.text.toString().toDoubleOrNull() ?: 0.0
        val keterangan = binding.editKeterangan.text.toString()
        val jenis = binding.dropdownJenis.text.toString()

        val uangMasuk = UangMasukEntity(
            tanggal = Date(),
            jam = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
            masukKe = masukKe,
            terimaDari = dari,
            keterangan = keterangan,
            jumlah = jumlah,
            jenis = jenis,
            fotoPath = null // Implement photo saving logic
        )

        viewModel.insert(uangMasuk)
        findNavController().navigateUp()
    }

    private fun setupFotoButton() {
        binding.buttonAmbilFoto.setOnClickListener {
            showImagePickerDialog()
        }

        binding.buttonHapusFoto.setOnClickListener {
            clearSelectedImage()
        }
    }

    private fun showImagePickerDialog() {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add Photo!")
        builder.setItems(options) { dialog, item ->
            when {
                options[item] == "Take Photo" -> {
                    // Implementasi untuk mengambil foto menggunakan kamera
                }
                options[item] == "Choose from Gallery" -> {
                    // Implementasi untuk memilih foto dari galeri
                }
                options[item] == "Cancel" -> dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun clearSelectedImage() {
        binding.imageFoto.setImageResource(R.drawable.ic_photo_placeholder)
        binding.buttonHapusFoto.visibility = View.GONE
        // Reset variabel yang menyimpan path foto
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}