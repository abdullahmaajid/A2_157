package com.example.projectakhir.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Penulis(
    @SerialName("id_penulis")
    val idPenulis: Int,

    @SerialName("nama_penulis")
    val namaPenulis: String,

    @SerialName("biografi")
    val biografi: String,

    @SerialName("kontak")
    val kontak: String
)