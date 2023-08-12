package com.example.inseminator.core.repository

import com.example.inseminator.core.data.api.RemoteDataSource
import com.example.inseminator.core.data.api.network.Resource
import com.example.inseminator.core.data.api.request.KonfirmasiRequest
import com.example.inseminator.core.data.api.request.LahirRequest
import com.example.inseminator.core.data.api.request.LoginRequest
import com.example.inseminator.core.data.api.request.ProfileRequest
import com.example.inseminator.core.data.api.request.UpbuntingRequest
import com.example.inseminator.core.data.api.response.DataResponse
import com.example.inseminator.core.data.api.response.LoginRespon
import com.example.inseminator.core.data.api.response.item.KonfirmasiItem
import com.example.myapplication.core.data.api.response.item.NotifItem
import com.inyongtisto.myhelper.extension.getErrorBody
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.lang.Exception

class CoreRepository (private val remoteDataSource: RemoteDataSource)  {
    fun login (data : LoginRequest)= flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.login(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(LoginRespon::class.java)?.message ?: "",null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan",null))}
    }
    fun province () = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.province().let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }
    fun regency (id: Int) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.regency(id).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                    }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e:Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }
    fun district (id: Int) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.district(id).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e:Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }
    fun village (id: Int) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.village(id).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e:Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }
    fun study () = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.study().let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }
    fun profile (token:String)= flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.profile(token).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(LoginRespon::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }
    fun editprofil (token:String, data: ProfileRequest)= flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.editprofil(token, data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(LoginRespon::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }

    fun rumpun () = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.rumpun().let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }

    fun history (token: String) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.history(token).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }

    fun pengajuan (token: String) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.pengajuan(token).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }

    fun konfirmasi (id: Int, token: String, konfirmasiRequest: KonfirmasiRequest) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.konfirmasi(id, token, konfirmasiRequest).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }
    fun upbunting (id: Int, token: String, upbuntingRequest: UpbuntingRequest) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.upbunting(id, token, upbuntingRequest).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }
   fun lahir (id: Int, token: String, lahirRequest: LahirRequest) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.lahir(id, token, lahirRequest).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }
                else {
                    emit(Resource.error(it.getErrorBody(DataResponse::class.java)?.message ?:"", null))
                }
            }
        }
        catch (e: Exception){emit(Resource.error(e.message?:"Terjadi Kesalahan", null))}
    }

    fun notifikasi(token: String) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.notifikasi(token).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                } else {
                    emit(
                        Resource.error(
                            it.getErrorBody(DataResponse::class.java)?.message ?: "",
                            null
                        )
                    )
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun countnotif(token: String) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.countnotif(token).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody(NotifItem::class.java)?.status ?: "", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun upnotif(token: String, id: Int) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.upnotif(token, id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody(NotifItem::class.java)?.status ?: "", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

}