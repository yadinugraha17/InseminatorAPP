package com.example.inseminator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.inseminator.R
import com.example.inseminator.core.data.api.network.State
import com.example.inseminator.databinding.ActivityHomeBinding
import com.example.inseminator.ui.home.HomeFragment
import com.example.inseminator.ui.login.LoginActivity
import com.example.inseminator.ui.login.LoginViewModel
import com.example.inseminator.ui.notifkasi.NotifikasiFragment
import com.example.inseminator.ui.profile.ProfileFragment
import com.inyongtisto.myhelper.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private var notifikasiBadge: View? = null
    private val viewModel: LoginViewModel by viewModel()
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        root = binding?.root
        setContentView(root)

        // Inisialisasi tampilan badge pada item "Notifikasi"
        notifikasiBadge = LayoutInflater.from(this).inflate(
            R.layout.badge_layout,
            binding?.menu,
            false)

        binding?.menu?.setItemSelected(R.id.home)
        openMainFragment()
        binding?.menu?.setOnItemSelectedListener {
            when (it) {

                R.id.home -> {
                    openMainFragment()
                }

                R.id.notifikasi -> {
                    val notifikasiFragment = NotifikasiFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, notifikasiFragment).commit()

                }

                R.id.profile -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, profileFragment).commit()
                }
            }
        }

    }

    fun refreshActivityContent() {
        countnotif()
    }

    private fun openMainFragment() {
        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, homeFragment)
        transaction.commit()
    }

    private fun countnotif() {
        viewModel.countnotif("Bearer ${LoginActivity.TOKEN_KEY}")
            .observe(this) { result ->
                when (result.state) {
                    State.SUCCESS -> {
                        val response = result.data
                        count = response?.jumlah_belum_baca!!
                        binding?.menu?.showBadge(R.id.notifikasi, count)
                        progress.dismiss()
                    }

                    State.LOADING -> {
                        progress.show()
                    }

                    State.ERROR -> {
//                        toastError(result.message.toString())
                    }
                }
            }
    }

    override fun onResume() {
        super.onResume()
        countnotif()
    }
}