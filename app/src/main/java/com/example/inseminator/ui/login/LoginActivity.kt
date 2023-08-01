package com.example.inseminator.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.inseminator.R
import com.example.inseminator.core.data.api.network.State
import com.example.inseminator.core.data.api.request.LoginRequest
import com.example.inseminator.core.data.api.response.item.LoginItem
import com.example.inseminator.core.session.SessionRepository
import com.example.inseminator.core.session.SessionViewModel
import com.example.inseminator.core.utilis.ViewModelUserFactory
import com.example.inseminator.databinding.ActivityLoginBinding
import com.example.inseminator.ui.HomeActivity
import com.inyongtisto.myhelper.base.BaseActivity
import com.inyongtisto.myhelper.base.BaseFragment
import com.inyongtisto.myhelper.extension.toastError
import com.inyongtisto.myhelper.extension.toastSuccess
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private val viewModel: LoginViewModel by viewModel()
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore("settings")
    private lateinit var sessionViewModel: SessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        root = binding?.root
        setContentView(root)
        sessionViewModel = ViewModelProvider(
            this, ViewModelUserFactory(
                SessionRepository.getInstance(this.datastore)
            )
        )[SessionViewModel::class.java]
        binding?.login?.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val hp = binding?.nohp?.text
        val pass = binding?.pass?.text
        val data = LoginRequest(hp.toString(), pass.toString())
        viewModel.login(data).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    val respon = it.data
                    val token = respon!![0].token
                    val login = LoginItem(token, respon[0].user_id, true)
                    val sessionRepository =
                        SessionRepository.getInstance(this.datastore)
                    lifecycleScope.launch {
                        sessionRepository.saveUser(login)
                    }

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    toastSuccess(token.toString())
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

    companion object {
        var TOKEN_KEY = "token"

    }
}