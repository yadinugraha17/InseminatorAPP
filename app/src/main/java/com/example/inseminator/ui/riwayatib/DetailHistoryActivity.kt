package com.example.inseminator.ui.riwayatib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.inseminator.R
import com.example.inseminator.core.data.api.network.State
import com.example.inseminator.core.data.api.request.KonfirmasiRequest
import com.example.inseminator.core.data.api.request.UpbuntingRequest
import com.example.inseminator.core.data.api.response.item.PengajuanItem
import com.example.inseminator.databinding.ActivityDetailHistoryBinding
import com.example.inseminator.databinding.ActivityHistoryBinding
import com.example.inseminator.ui.login.LoginActivity
import com.example.inseminator.ui.login.LoginViewModel
import com.google.gson.Gson
import com.inyongtisto.myhelper.base.BaseActivity
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailHistoryActivity : BaseActivity() {
    private var _binding: ActivityDetailHistoryBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private val viewModel: LoginViewModel by viewModel()
    private var idib: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        root = binding?.root
        setContentView(root)
        detailhistory()
        binding?.BTLahir?.setOnClickListener {
            bunting()
        }
    }

    private fun detailhistory () {
        val json = intent?.getStringExtra("history")
        val ternak = Gson().fromJson(json, PengajuanItem::class.java)
        idib = ternak.id
        binding?.tvNoregis?.text = ternak.ternak.no_regis
        binding?.tvJenisSemen?.text = ternak.rumpun.nama
        binding?.tvTglBirahi?.text = ternak.waktu_birahi
        binding?.tvJamBirahi?.text = ternak.jam_birahi
        binding?.tvTglIb?.text = ternak.tgl_ib
        binding?.tvTglBunting?.text = ternak.tgl_pkb
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
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
    private fun bunting() {
        viewModel.upbunting(idib,"Bearer ${LoginActivity.TOKEN_KEY}", UpbuntingRequest(getCurrentDate()))
            .observe(this){
                when (it.state) {
                    State.SUCCESS -> {
                        progress.dismiss()
                        val successMessage = "Bunting berhasil dikonfirmasi pada tanggal ${getCurrentDate()}"
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
                        toastError(it.message.toString())
                    }
                }
            }
    }

}