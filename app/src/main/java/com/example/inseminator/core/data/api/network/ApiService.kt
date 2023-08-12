package com.example.inseminator.core.data.api.network

import com.example.inseminator.core.data.api.request.KonfirmasiRequest
import com.example.inseminator.core.data.api.request.LahirRequest
import com.example.inseminator.core.data.api.request.LoginRequest
import com.example.inseminator.core.data.api.request.ProfileRequest
import com.example.inseminator.core.data.api.request.UpbuntingRequest
import com.example.inseminator.core.data.api.response.DataResponse
import com.example.inseminator.core.data.api.response.LoginRespon
import com.example.inseminator.core.data.api.response.item.DistrictItem
import com.example.inseminator.core.data.api.response.item.HistoryItem
import com.example.inseminator.core.data.api.response.item.KonfirmasiItem
import com.example.inseminator.core.data.api.response.item.PengajuanItem
import com.example.myapplication.core.data.api.response.item.PendItem
import com.example.inseminator.core.data.api.response.item.ProfileItem
import com.example.myapplication.core.data.api.response.item.NotifItem
import com.example.myapplication.core.data.api.response.item.NotifikasiItem
import com.example.myapplication.core.data.api.response.item.ProvinceItem
import com.example.myapplication.core.data.api.response.item.RegencyItem
import com.example.myapplication.core.data.api.response.item.RumpunItem
import com.example.myapplication.core.data.api.response.item.VillageItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("login/insiminator")
    suspend fun login(@Body login: LoginRequest): Response<LoginRespon>

    @GET("detail-insiminator")
    suspend fun profile(@Header("Authorization") token: String): Response<DataResponse<List<ProfileItem>>>
    @PUT("update/insiminator")
    suspend fun editprofile(@Header("Authorization") token: String,  @Body profile: ProfileRequest): Response<DataResponse<List<ProfileItem>>>

    @GET("provinsi")
    suspend fun province(): Response<DataResponse<List<ProvinceItem>>>

    @GET("kabupaten/{id}")
    suspend fun regency(@Path("id") id: Int): Response<DataResponse<List<RegencyItem>>>

    @GET("kecamatan/{id}")
    suspend fun district(@Path("id") id: Int): Response<DataResponse<List<DistrictItem>>>

    @GET("desa/{id}")
    suspend fun village(@Path("id") id: Int): Response<DataResponse<List<VillageItem>>>

    @GET("pendidikan")
    suspend fun study(): Response<DataResponse<List<PendItem>>>

    @GET("rumpun")
    suspend fun rumpun(): Response<DataResponse<List<RumpunItem>>>

    @POST("insiminasi")
    suspend fun inseminasi(@Header("Authorization") token: String)

    @GET("riwayat/pengajuan/insiminator")
    suspend fun pengajuan(@Header("Authorization") token: String ): Response<DataResponse<List<PengajuanItem>>>

    @GET("riwayat/pengajuan/status/aktif")
    suspend fun history(@Header("Authorization") token: String ): Response<DataResponse<List<HistoryItem>>>

    @PUT("insiminasi/{id}")
    suspend fun konfrimasi(@Path("id") id: Int, @Header("Authorization") token: String , @Body konfirmasiRequest: KonfirmasiRequest): Response<DataResponse<List<KonfirmasiItem>>>

    @PUT("update/kebuntingan/{id}")
    suspend fun upbunting(@Path("id") id: Int, @Header("Authorization") token: String , @Body upbuntingRequest: UpbuntingRequest): Response<DataResponse<List<KonfirmasiItem>>>

    @PUT("update/lahir/{id}")
    suspend fun lahir(@Path("id") id: Int, @Header("Authorization") token: String , @Body lahirRequest: LahirRequest): Response<DataResponse<List<KonfirmasiItem>>>

    @GET("notifikasi")
    suspend fun notifikasi(@Header("Authorization") token: String): Response<DataResponse<List<NotifikasiItem>>>

    @GET("jumlah/notifikasi")
    suspend fun countnotif(@Header("Authorization") token: String): Response<NotifItem>

    @PUT("update/notifikasi/{id}")
    suspend fun upnotif(@Header("Authorization") token: String, @Path("id") id: Int): Response<NotifItem>
}