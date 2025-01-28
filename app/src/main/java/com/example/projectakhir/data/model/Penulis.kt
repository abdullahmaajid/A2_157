// Mendeklarasikan package tempat file ini berada
package com.example.projectakhir.data.model

// Mengimpor anotasi untuk serialisasi JSON menggunakan kotlinx.serialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Memberikan anotasi @Serializable untuk membuat kelas ini dapat diserialisasi (digunakan dengan JSON)
@Serializable
data class Penulis( // Mendefinisikan data class bernama Penulis
    @SerialName("id_penulis") // Menentukan nama properti JSON yang terkait dengan properti ini
    val idPenulis: Int, // Properti untuk menyimpan ID unik penulis, tipe datanya adalah Int (angka)

    @SerialName("nama_penulis") // Menentukan nama properti JSON yang terkait dengan properti ini
    val namaPenulis: String, // Properti untuk menyimpan nama lengkap penulis, tipe datanya adalah String (teks)

    @SerialName("biografi") // Menentukan nama properti JSON yang terkait dengan properti ini
    val biografi: String, // Properti untuk menyimpan biografi singkat penulis, tipe datanya adalah String

    @SerialName("kontak") // Menentukan nama properti JSON yang terkait dengan properti ini
    val kontak: String // Properti untuk menyimpan informasi kontak penulis (email/telepon), tipe datanya adalah String
)
