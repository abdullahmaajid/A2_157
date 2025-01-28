// Package deklarasi untuk lokasi file ini
package com.example.projectakhir.data.repository

// Import model dan service terkait Penerbit
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.service.PenerbitService

// Interface untuk Repository Penerbit
// Berfungsi sebagai kontrak untuk operasi CRUD pada data Penerbit
interface PenerbitRepository {
    suspend fun getPenerbit(): List<Penerbit> // Fungsi untuk mendapatkan semua data Penerbit
    suspend fun insertPenerbit(penerbit: Penerbit) // Fungsi untuk menambahkan data Penerbit baru
    suspend fun updatePenerbit(idPenerbit: Int, penerbit: Penerbit) // Fungsi untuk memperbarui data Penerbit berdasarkan ID
    suspend fun deletePenerbit(idPenerbit: Int) // Fungsi untuk menghapus data Penerbit berdasarkan ID
    suspend fun getPenerbitById(idPenerbit: Int): Penerbit // Fungsi untuk mendapatkan data Penerbit berdasarkan ID
}

// Implementasi dari PenerbitRepository menggunakan Network/API
class NetworkPenerbitRepository(private val penerbitService: PenerbitService) : PenerbitRepository {

    // Implementasi fungsi untuk mendapatkan semua data Penerbit
    override suspend fun getPenerbit(): List<Penerbit> = penerbitService.getPenerbit()

    // Implementasi fungsi untuk menambahkan data Penerbit baru
    override suspend fun insertPenerbit(penerbit: Penerbit) {
        penerbitService.insertPenerbit(penerbit)
    }

    // Implementasi fungsi untuk memperbarui data Penerbit berdasarkan ID
    override suspend fun updatePenerbit(idPenerbit: Int, penerbit: Penerbit) {
        penerbitService.updatePenerbit(idPenerbit, penerbit)
    }

    // Implementasi fungsi untuk menghapus data Penerbit berdasarkan ID
    override suspend fun deletePenerbit(idPenerbit: Int) {
        try {
            // Memanggil API untuk menghapus data Penerbit
            val response = penerbitService.deletePenerbit(idPenerbit)
            // Mengecek apakah respons berhasil
            if (!response.isSuccessful) {
                // Jika gagal, lempar Exception dengan kode HTTP
                throw Exception("Failed to delete penerbit. HTTP Status Code: ${response.code()}")
            } else {
                // Jika berhasil, cetak pesan sukses
                println(response.message())
            }
        } catch (e: Exception) {
            // Menangkap dan melempar ulang exception jika terjadi error
            throw e
        }
    }

    // Implementasi fungsi untuk mendapatkan data Penerbit berdasarkan ID
    override suspend fun getPenerbitById(idPenerbit: Int): Penerbit {
        return penerbitService.getPenerbitById(idPenerbit)
    }
}
