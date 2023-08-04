package com.example.inseminator.ui.notifkasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inseminator.core.adapter.NotifikasiAdapter
import com.example.inseminator.core.data.api.network.State
import com.example.inseminator.databinding.FragmentNotifikasiBinding
import com.example.inseminator.ui.login.LoginActivity
import com.example.inseminator.ui.login.LoginViewModel
import com.inyongtisto.myhelper.base.BaseFragment
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotifikasiFragment : BaseFragment() {
    private var _binding: FragmentNotifikasiBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotifikasiBinding.inflate(layoutInflater)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifikasi()
    }

    private fun notifikasi() {
        viewModel.notifikasi("Bearer ${LoginActivity.TOKEN_KEY}").observe(viewLifecycleOwner) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    val response = it.data
                    val historyAdapter = NotifikasiAdapter()
                    historyAdapter.setData(response)
                    with(binding?.rvNotif) {
                        this?.adapter = historyAdapter
                        this?.layoutManager = LinearLayoutManager(
                            requireContext(),
                            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                            false
                        )
                        this?.setHasFixedSize(true)
                    }

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