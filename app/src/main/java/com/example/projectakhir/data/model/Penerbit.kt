// Mendeklarasikan package tempat file ini berada
package com.example.projectakhir.data.model

// Mengimpor anotasi untuk serialisasi JSON menggunakan kotlinx.serialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Memberikan anotasi @Serializable untuk membuat kelas ini dapat diserialisasi (digunakan dengan JSON)
@Serializable
data class Penerbit( // Mendefinisikan data class bernama Penerbit
    @SerialName("id_penerbit") // Menentukan nama properti JSON yang terkait dengan properti ini
    val idPenerbit: Int, // Properti untuk menyimpan ID penerbit, tipe datanya adalah Int (angka)

    @SerialName("nama_penerbit") // Menentukan nama properti JSON yang terkait dengan properti ini
    val namaPenerbit: String, // Properti untuk menyimpan nama penerbit, tipe datanya adalah String (teks)

    @SerialName("alamat_penerbit") // Menentukan nama properti JSON yang terkait dengan properti ini
    val alamatPenerbit: String, // Properti untuk menyimpan alamat penerbit, tipe datanya adalah String

    @SerialName("telepon_penerbit") // Menentukan nama properti JSON yang terkait dengan properti ini
    val teleponPenerbit: String // Properti untuk menyimpan nomor telepon penerbit, tipe datanya adalah String
)
