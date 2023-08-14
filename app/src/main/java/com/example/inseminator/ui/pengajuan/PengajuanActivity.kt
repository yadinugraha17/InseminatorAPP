package com.example.inseminator.ui.pengajuan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inseminator.core.adapter.PengajuanAdapter
import com.example.inseminator.core.data.api.network.State
import com.example.inseminator.core.data.api.response.item.PengajuanItem
import com.example.inseminator.databinding.ActivityPengajuanBinding
import com.example.inseminator.ui.login.LoginActivity
import com.example.inseminator.ui.login.LoginViewModel
import com.example.inseminator.ui.riwayatib.HistoryActivity
import com.google.gson.Gson
import com.inyongtisto.myhelper.base.BaseActivity
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class PengajuanActivity : BaseActivity() {
    private var _binding: ActivityPengajuanBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPengajuanBinding.inflate(layoutInflater)
        root = binding?.root
        setContentView(root)
    }
    private fun pengajuan() {
        viewModel.pengajuan("Bearer ${LoginActivity.TOKEN_KEY}")
            .observe(this){
                when (it.state) {
                    State.SUCCESS -> {
                        progress.dismiss()
                        val response = it.data
                        val pengajuanAdapter = PengajuanAdapter()
                        pengajuanAdapter.setData (response)
                        with(binding?.rvPengajuan) {
                            this?.adapter = pengajuanAdapter
                            this?.layoutManager = LinearLayoutManager(
                                this@PengajuanActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            this?.setHasFixedSize(true)
                        }
                        pengajuanAdapter.setOnItemClickCallback((object :
                            PengajuanAdapter.OnItemClickCallback {
                            override fun onItemClicked(news: PengajuanItem) {
                                val json = Gson().toJson(news)
                                val intent = Intent(
                                    this@PengajuanActivity,
                                    DetailPengajuanActivity::class.java
                                )
                                intent.putExtra("pengajuan", json)
                                startActivity(intent)
                                finish()
                            }

                        }))

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
    override fun onResume() {
        super.onResume()
        pengajuan()
    }
}