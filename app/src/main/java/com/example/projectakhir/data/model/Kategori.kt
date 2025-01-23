package com.example.projectakhir.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kategori(
    @SerialName("id_kategori")
    val idKategori: Int,

    @SerialName("nama_kategori")
    val namaKategori: String,

    @SerialName("deskripsi_kategori")
    val deskripsiKategori: String
)