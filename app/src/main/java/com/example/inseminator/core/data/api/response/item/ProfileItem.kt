package com.example.inseminator.core.data.api.response.item

import com.example.myapplication.core.data.api.response.item.Pendidikan
import com.example.myapplication.core.data.api.response.item.Province
import com.example.myapplication.core.data.api.response.item.Regency
import com.example.myapplication.core.data.api.response.item.Village

data class ProfileItem(
    val id: Int,
    val nik: String,
    val wilayah_kerja_kab: String,
    val nama: String,
    val no_hp: String,
    val sertifikat: String,
    val status_insimantor: String,
    val koordinat: String,
    val detail_wilayah_kerja: String,
    val pendidikan_id: String,
    val alamat: String,
    val province_id: String,
    val regency_id: String,
    val district_id: String,
    val village_id: String,
    val pendidikan: Pendidikan,
    val province: Province,
    val regency: Regency,
    val district: District,
    val village: Village,
    val wilayah_kab: Regency
)