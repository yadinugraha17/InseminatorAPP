package com.example.inseminator.ui.pengajuan

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inseminator.R
import com.example.inseminator.core.adapter.PengajuanAdapter
import com.example.inseminator.core.data.api.network.State
import com.example.inseminator.core.data.api.request.KonfirmasiRequest
import com.example.inseminator.core.data.api.response.item.HistoryItem
import com.example.inseminator.core.data.api.response.item.KonfirmasiItem
import com.example.inseminator.core.data.api.response.item.PengajuanItem
import com.example.inseminator.databinding.ActivityDetailPengajuanBinding
import com.example.inseminator.databinding.ActivityPengajuanBinding
import com.example.inseminator.ui.login.LoginActivity
import com.example.inseminator.ui.login.LoginViewModel
import com.example.inseminator.ui.riwayatib.HistoryActivity
import com.google.gson.Gson
import com.inyongtisto.myhelper.base.BaseActivity
import com.inyongtisto.myhelper.extension.toastError
import com.inyongtisto.myhelper.extension.toastSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailPengajuanActivity : BaseActivity() {
    private var _binding: ActivityDetailPengajuanBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private val viewModel: LoginViewModel by viewModel()
    private var idib: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailPengajuanBinding.inflate(layoutInflater)
        root = binding?.root
        setContentView(root)
        detailpengajuan()
        binding?.copy?.setOnClickListener {
            copyTextToClipboard(binding?.tvNohp?.text.toString())
            toastSuccess("No HP Berhasil Disalin")
        }

        binding?.btnKonfirmasi?.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun detailpengajuan () {
        val json = intent?.getStringExtra("pengajuan")
        val ternak = Gson().fromJson(json, PengajuanItem::class.java)
        idib = ternak.id
        binding?.tvPeternak?.text = ternak.peternak.nama
        binding?.tvNoregis?.text = ternak.ternak.no_regis
        binding?.tvJenisSemen?.text = ternak.rumpun.nama
        binding?.tvTglBirahi?.text = ternak.waktu_birahi
        binding?.tvJamBirahi?.text = ternak.jam_birahi
        if (ternak.tgl_ib != null) {
            binding?.tvTglIb?.text = ternak.tgl_ib
        } else {
            binding?.tvTglIb?.text = "Belum Diinseminasi"
        }
        if (ternak.status_kebuntingan == "0") {
            binding?.tvBunting?.text = "Belum Bunting"
        } else if (ternak.status_kebuntingan == "1") {
            binding?.tvBunting?.text = "Bunting"
        } else {
            // Jika nilainya bukan "0" atau "1", tampilkan nilai sebenarnya
            binding?.tvBunting?.text = ternak.status_kebuntingan
        }
        // Tampilkan teks "Belum" jika status kelahiran adalah "0", jika "1" tampilkan "Sudah"
        if (ternak.status_lahir == "0") {
            binding?.tvLahir?.text = "Belum Lahir"
        } else if (ternak.status_lahir == "1") {
            binding?.tvLahir?.text = "Lahir"
        } else {
            // Jika nilainya bukan "0" atau "1", tampilkan nilai sebenarnya
            binding?.tvLahir?.text = ternak.status_lahir
        }
        binding?.tvNohp?.text = ternak.peternak.no_hp
    }
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi")
        builder.setMessage("Apakah Anda yakin ingin mengkonfirmasi inseminasi?")
        builder.setPositiveButton("Ya") { dialog, which ->
            viewModel.konfirmasi(idib,"Bearer ${LoginActivity.TOKEN_KEY}", KonfirmasiRequest(getCurrentDate()))
                .observe(this){
                    when (it.state) {
                        State.SUCCESS -> {
                            progress.dismiss()
                            val successMessage = "Inseminasi berhasil dikonfirmasi pada tanggal ${getCurrentDate()}"
                            Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, HistoryActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        State.LOADING -> {
                            progress.show()
                        }

                        State.ERROR -> {
                            progress.dismiss()
                            val successMessage = "Inseminasi berhasil dikonfirmasi pada tanggal ${getCurrentDate()}"
                            Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, HistoryActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
        }
        builder.setNegativeButton("Tidak") { dialog, which ->
            // Tidak perlu melakukan apa-apa karena pengguna memilih "Tidak"
        }
        val dialog = builder.create()
        dialog.show()
    }
    private fun copyTextToClipboard(text: String) {
        val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = android.content.ClipData.newPlainText("No Hp Tersalin", text)
        clipboard.setPrimaryClip(clip)
    }
}