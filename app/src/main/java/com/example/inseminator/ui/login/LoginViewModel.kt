package com.example.inseminator.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.inseminator.core.data.api.request.KonfirmasiRequest
import com.example.inseminator.core.data.api.request.LahirRequest
import com.example.inseminator.core.data.api.request.LoginRequest
import com.example.inseminator.core.data.api.request.ProfileRequest
import com.example.inseminator.core.data.api.request.UpbuntingRequest
import com.example.inseminator.core.data.api.response.item.KonfirmasiItem
import com.example.inseminator.core.repository.CoreRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class LoginViewModel (private val coreRepository: CoreRepository):ViewModel() {
    fun login (data: LoginRequest)= coreRepository.login(data).asLiveData()
    fun profile (token:String)= coreRepository.profile(token).asLiveData()
    fun editprofile (token:String, data:ProfileRequest)= coreRepository.editprofil(token,data).asLiveData()
    fun province ()= coreRepository.province().asLiveData()
    fun regency (id: Int)= coreRepository.regency(id).asLiveData()
    fun district (id: Int)= coreRepository.district(id).asLiveData()
    fun village (id: Int)= coreRepository.village(id).asLiveData()
    fun study ()= coreRepository.study().asLiveData()
    fun rumpun ()= coreRepository.rumpun().asLiveData()
    fun history (token: String)= coreRepository.history(token).asLiveData()
    fun pengajuan (token: String)= coreRepository.pengajuan(token).asLiveData()
    fun konfirmasi (id:Int, token: String, konfirmasiRequest: KonfirmasiRequest)= coreRepository.konfirmasi(id, token, konfirmasiRequest).asLiveData()
    fun upbunting (id:Int, token: String, upbuntingRequest: UpbuntingRequest)= coreRepository.upbunting(id, token, upbuntingRequest).asLiveData()
    fun lahir (id:Int, token: String, lahirRequest: LahirRequest)= coreRepository.lahir(id, token, lahirRequest).asLiveData()
    fun notifikasi (token: String)= coreRepository.notifikasi(token).asLiveData()
    fun countnotif(token: String)=coreRepository.countnotif(token).asLiveData()
    fun upnotif(token: String, id: Int)=coreRepository.upnotif(token, id).asLiveData()
}