// Package deklarasi untuk lokasi file ini
package com.example.projectakhir.data.repository

// Import model dan service terkait Penulis
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.service.PenulisService

// Interface untuk Repository Penulis
// Berfungsi sebagai kontrak untuk operasi CRUD pada data Penulis
interface PenulisRepository {
    suspend fun getPenulis(): List<Penulis> // Fungsi untuk mendapatkan semua data Penulis
    suspend fun insertPenulis(penulis: Penulis) // Fungsi untuk menambahkan data Penulis baru
    suspend fun updatePenulis(idPenulis: Int, penulis: Penulis) // Fungsi untuk memperbarui data Penulis berdasarkan ID
    suspend fun deletePenulis(idPenulis: Int) // Fungsi untuk menghapus data Penulis berdasarkan ID
    suspend fun getPenulisById(idPenulis: Int): Penulis // Fungsi untuk mendapatkan data Penulis berdasarkan ID
}

// Implementasi dari PenulisRepository menggunakan Network/API
class NetworkPenulisRepository(private val penulisService: PenulisService) : PenulisRepository {

    // Implementasi fungsi untuk mendapatkan semua data Penulis
    override suspend fun getPenulis(): List<Penulis> = penulisService.getPenulis()

    // Implementasi fungsi untuk menambahkan data Penulis baru
    override suspend fun insertPenulis(penulis: Penulis) {
        penulisService.insertPenulis(penulis)
    }

    // Implementasi fungsi untuk memperbarui data Penulis berdasarkan ID
    override suspend fun updatePenulis(idPenulis: Int, penulis: Penulis) {
        penulisService.updatePenulis(idPenulis, penulis)
    }

    // Implementasi fungsi untuk menghapus data Penulis berdasarkan ID
    override suspend fun deletePenulis(idPenulis: Int) {
        try {
            // Memanggil API untuk menghapus data Penulis
            val response = penulisService.deletePenulis(idPenulis)
            // Mengecek apakah respons berhasil
            if (!response.isSuccessful) {
                // Jika gagal, lempar Exception dengan kode HTTP
                throw Exception("Gagal menghapus penulis. Kode Status HTTP: ${response.code()}")
            } else {
                // Jika berhasil, cetak pesan sukses
                println(response.message())
            }
        } catch (e: Exception) {
            // Menangkap dan melempar ulang exception jika terjadi error
            throw e
        }
    }

    // Implementasi fungsi untuk mendapatkan data Penulis berdasarkan ID
    override suspend fun getPenulisById(idPenulis: Int): Penulis {
        return penulisService.getPenulisById(idPenulis)
    }
}
