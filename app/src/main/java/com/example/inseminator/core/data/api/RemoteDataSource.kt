package com.example.inseminator.core.data.api

import com.example.inseminator.core.data.api.network.ApiService
import com.example.inseminator.core.data.api.request.KonfirmasiRequest
import com.example.inseminator.core.data.api.request.LahirRequest
import com.example.inseminator.core.data.api.request.LoginRequest
import com.example.inseminator.core.data.api.request.ProfileRequest
import com.example.inseminator.core.data.api.request.UpbuntingRequest
import com.example.inseminator.core.data.api.response.item.KonfirmasiItem

class RemoteDataSource (private val apiService : ApiService){
    suspend fun login (data : LoginRequest) = apiService.login(data)
    suspend fun province () = apiService.province()
    suspend fun regency (id:Int) = apiService.regency(id)
    suspend fun district (id:Int) = apiService.district(id)
    suspend fun village (id:Int) = apiService.village(id)
    suspend fun study () = apiService.study()
    suspend fun profile (token:String) = apiService.profile(token)
    suspend fun editprofil(token: String, data: ProfileRequest) = apiService.editprofile(token, data)
    suspend fun rumpun () = apiService.rumpun()
    suspend fun history (token:String) = apiService.history(token)
    suspend fun pengajuan (token:String) = apiService.pengajuan(token)
    suspend fun konfirmasi (id:Int, token: String, konfirmasiRequest: KonfirmasiRequest) = apiService.konfrimasi(id, token, konfirmasiRequest)
    suspend fun upbunting (id:Int, token: String, upbuntingRequest: UpbuntingRequest) = apiService.upbunting(id, token, upbuntingRequest)
    suspend fun lahir (id:Int, token: String, lahirRequest: LahirRequest) = apiService.lahir(id, token, lahirRequest)
    suspend fun notifikasi (token:String) = apiService.notifikasi(token)
    suspend fun countnotif(token: String) = apiService.countnotif(token)
    suspend fun upnotif(token: String, id: Int) = apiService.upnotif(token, id)
}