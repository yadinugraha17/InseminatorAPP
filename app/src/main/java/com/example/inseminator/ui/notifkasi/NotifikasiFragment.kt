package com.example.inseminator.ui.notifkasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inseminator.core.adapter.NotifikasiAdapter
import com.example.inseminator.core.data.api.network.State
import com.example.inseminator.databinding.FragmentNotifikasiBinding
import com.example.inseminator.ui.HomeActivity
import com.example.inseminator.ui.login.LoginActivity
import com.example.inseminator.ui.login.LoginViewModel
import com.example.myapplication.core.data.api.response.item.NotifikasiItem
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

    private fun refreshActivityContent() {
        // Call the refreshActivityContent() method of the parent Activity
        (activity as? HomeActivity)?.refreshActivityContent()
    }

    fun performRefresh() {

        refreshActivityContent()}

    private fun notifikasi() {
        viewModel.notifikasi("Bearer ${LoginActivity.TOKEN_KEY}")
            .observe(viewLifecycleOwner) {
                when (it.state) {
                    State.SUCCESS -> {
                        progress.dismiss()
                        val response = it.data
                        val notifikasiAdapter = NotifikasiAdapter()
                        notifikasiAdapter.setData(response)
                        with(binding?.rvNotif) {
                            this?.adapter = notifikasiAdapter
                            this?.layoutManager = LinearLayoutManager(
                                requireContext(),
                                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                                false
                            )
                            this?.setHasFixedSize(true)
                        }
                        notifikasiAdapter.setOnItemClickCallback((object :
                            NotifikasiAdapter.OnItemClickCallback {
                            override fun onItemClicked(news: NotifikasiItem) {
                                viewModel.upnotif("Bearer ${LoginActivity.TOKEN_KEY}", news.id)
                                    .observe(viewLifecycleOwner) {
                                        when (it.state) {
                                            State.SUCCESS -> {
                                                notifikasi()
                                                performRefresh()
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
}