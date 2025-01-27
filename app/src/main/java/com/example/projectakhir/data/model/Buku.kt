package com.example.projectakhir.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Buku  (
    @SerialName("id_buku")
    val idBuku: Int,

    @SerialName("nama_buku")
    val namaBuku: String,

    @SerialName("deskripsi_buku")
    val deskripsiBuku: String,

    @SerialName("tanggal_terbit")
    val tanggalTerbit: String,

    @SerialName("status_buku")
    val statusBuku: String,

    @SerialName("id_kategori")
    val idKategori: Int,

    @SerialName("id_penulis")
    val idPenulis: Int,

    @SerialName("id_penerbit")
    val idPenerbit: Int,

    )



