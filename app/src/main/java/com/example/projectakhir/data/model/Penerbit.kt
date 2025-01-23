package com.example.projectakhir.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Penerbit(
    @SerialName("id_penerbit")
    val idPenerbit: Int,

    @SerialName("nama_penerbit")
    val namaPenerbit: String,

    @SerialName("alamat_penerbit")
    val alamatPenerbit: String,

    @SerialName("telepon_penerbit")
    val teleponPenerbit: String
)