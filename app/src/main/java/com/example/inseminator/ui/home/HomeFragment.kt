package com.example.inseminator.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.inseminator.R
import com.example.inseminator.core.data.api.network.State
import com.example.inseminator.core.session.SessionRepository
import com.example.inseminator.core.session.SessionViewModel
import com.example.inseminator.core.utilis.ViewModelUserFactory
import com.example.inseminator.databinding.FragmentHomeBinding
import com.example.inseminator.ui.login.LoginActivity.Companion.TOKEN_KEY
import com.example.inseminator.ui.login.LoginViewModel
import com.example.inseminator.ui.pengajuan.PengajuanActivity
import com.example.inseminator.ui.riwayatib.HistoryActivity
import com.inyongtisto.myhelper.base.BaseFragment
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        root = binding?.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.llHistory?.setOnClickListener {
            val intent = Intent (requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }
        binding?.llPengajuan?.setOnClickListener {
            val intent = Intent (requireContext(), PengajuanActivity::class.java)
            startActivity(intent)
        }
        profile()
    }

    private fun profile() {
        viewModel.profile("Bearer $TOKEN_KEY")
            .observe(viewLifecycleOwner) {
                when (it.state) {
                    State.SUCCESS -> {
                        val respons = it.data
                        binding?.name?.text = respons?.get(0)?.nama
                        progress.dismiss()
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