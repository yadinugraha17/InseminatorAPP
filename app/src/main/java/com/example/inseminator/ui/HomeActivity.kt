package com.example.inseminator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.inseminator.R
import com.example.inseminator.databinding.ActivityHomeBinding
import com.example.inseminator.ui.home.HomeFragment
import com.example.inseminator.ui.notifkasi.NotifikasiFragment
import com.example.inseminator.ui.profile.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        root = binding?.root
        setContentView(root)

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

    private fun openMainFragment() {
        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, homeFragment)
        transaction.commit()
    }
}