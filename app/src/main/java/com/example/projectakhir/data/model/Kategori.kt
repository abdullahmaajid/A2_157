// Mendeklarasikan package tempat file ini berada
package com.example.projectakhir.data.model

// Mengimpor anotasi untuk serialisasi JSON menggunakan kotlinx.serialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Memberikan anotasi @Serializable untuk membuat kelas ini dapat diserialisasi (digunakan dengan JSON)
@Serializable
data class Kategori( // Mendefinisikan data class bernama Kategori
    @SerialName("id_kategori") // Menentukan nama properti JSON yang terkait dengan properti ini
    val idKategori: Int, // Properti untuk menyimpan ID kategori, tipe datanya adalah Int (angka)

    @SerialName("nama_kategori") // Menentukan nama properti JSON yang terkait dengan properti ini
    val namaKategori: String, // Properti untuk menyimpan nama kategori, tipe datanya adalah String (teks)

    @SerialName("deskripsi_kategori") // Menentukan nama properti JSON yang terkait dengan properti ini
    val deskripsiKategori: String // Properti untuk menyimpan deskripsi kategori, tipe datanya adalah String
)
