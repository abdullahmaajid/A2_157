// Package deklarasi untuk tempat file ini berada
package com.example.projectakhir.data.repository

// Mengimpor model Kategori dan layanan KategoriService untuk operasi data
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.service.KategoriService

// Interface untuk Repository Kategori
// Berfungsi sebagai kontrak untuk mengelola data Kategori
interface KategoriRepository {
    suspend fun getKategori(): List<Kategori> // Fungsi untuk mendapatkan semua data Kategori
    suspend fun insertKategori(kategori: Kategori) // Fungsi untuk menambahkan data Kategori baru
    suspend fun updateKategori(idKategori: Int, kategori: Kategori) // Fungsi untuk memperbarui data Kategori berdasarkan ID
    suspend fun deleteKategori(idKategori: Int) // Fungsi untuk menghapus data Kategori berdasarkan ID
    suspend fun getKategoriById(idKategori: Int): Kategori // Fungsi untuk mendapatkan data Kategori berdasarkan ID
}

// Implementasi dari KategoriRepository menggunakan Network/API
class NetworkKategoriRepository(private val kategoriService: KategoriService) : KategoriRepository {

    // Implementasi fungsi untuk mendapatkan semua data Kategori
    override suspend fun getKategori(): List<Kategori> = kategoriService.getKategori()

    // Implementasi fungsi untuk menambahkan data Kategori baru
    override suspend fun insertKategori(kategori: Kategori) {
        kategoriService.insertKategori(kategori)
    }

    // Implementasi fungsi untuk memperbarui data Kategori berdasarkan ID
    override suspend fun updateKategori(idKategori: Int, kategori: Kategori) {
        kategoriService.updateKategori(idKategori, kategori)
    }

    // Implementasi fungsi untuk menghapus data Kategori berdasarkan ID
    override suspend fun deleteKategori(idKategori: Int) {
        try {
            // Memanggil API untuk menghapus data Kategori
            val response = kategoriService.deleteKategori(idKategori)
            // Mengecek apakah respons berhasil
            if (!response.isSuccessful) {
                // Jika tidak berhasil, lempar Exception dengan kode HTTP
                throw Exception("Failed to delete kategori. HTTP Status Code: ${response.code()}")
            } else {
                // Jika berhasil, tampilkan pesan sukses
                println(response.message())
            }
        } catch (e: Exception) {
            // Menangkap dan melempar ulang exception jika terjadi error
            throw e
        }
    }

    // Implementasi fungsi untuk mendapatkan data Kategori berdasarkan ID
    override suspend fun getKategoriById(idKategori: Int): Kategori {
        return kategoriService.getKategoriById(idKategori)
    }
}
