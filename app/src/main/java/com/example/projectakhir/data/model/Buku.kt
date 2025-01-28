// Mendeklarasikan package tempat file ini berada
package com.example.projectakhir.data.model

// Mengimpor anotasi untuk serialisasi JSON menggunakan kotlinx.serialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Memberikan anotasi @Serializable untuk membuat kelas ini dapat diserialisasi (digunakan dengan JSON)
@Serializable
data class Buku( // Mendefinisikan data class bernama Buku
    @SerialName("id_buku") // Menentukan nama properti JSON yang terkait dengan properti ini
    val idBuku: Int, // Properti untuk menyimpan ID buku, tipe datanya adalah Int (angka)

    @SerialName("nama_buku") // Menentukan nama properti JSON yang terkait dengan properti ini
    val namaBuku: String, // Properti untuk menyimpan nama buku, tipe datanya adalah String (teks)

    @SerialName("deskripsi_buku") // Menentukan nama properti JSON yang terkait dengan properti ini
    val deskripsiBuku: String, // Properti untuk menyimpan deskripsi buku, tipe datanya adalah String

    @SerialName("tanggal_terbit") // Menentukan nama properti JSON yang terkait dengan properti ini
    val tanggalTerbit: String, // Properti untuk menyimpan tanggal terbit buku, tipe datanya adalah String

    @SerialName("status_buku") // Menentukan nama properti JSON yang terkait dengan properti ini
    val statusBuku: String, // Properti untuk menyimpan status buku (misalnya "tersedia" atau "dipinjam"), tipe datanya adalah String

    @SerialName("id_kategori") // Menentukan nama properti JSON yang terkait dengan properti ini
    val idKategori: Int, // Properti untuk menyimpan ID kategori buku, tipe datanya adalah Int

    @SerialName("id_penulis") // Menentukan nama properti JSON yang terkait dengan properti ini
    val idPenulis: Int, // Properti untuk menyimpan ID penulis buku, tipe datanya adalah Int

    @SerialName("id_penerbit") // Menentukan nama properti JSON yang terkait dengan properti ini
    val idPenerbit: Int // Properti untuk menyimpan ID penerbit buku, tipe datanya adalah Int
)
