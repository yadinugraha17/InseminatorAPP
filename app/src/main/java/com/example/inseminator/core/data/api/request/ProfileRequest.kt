package com.example.inseminator.core.data.api.request

data class ProfileRequest(
    val nik: String,
    val nama: String,
    val no_hp: String,
    val alamat: String,
    val pendidikan: Int,
    val province_id: Int,
    val regency_id: Int,
    val district_id: Int,
    val village_id: String
)
