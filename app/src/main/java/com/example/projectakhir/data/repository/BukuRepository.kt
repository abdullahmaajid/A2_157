// Package deklarasi untuk tempat file ini berada
package com.example.projectakhir.data.repository

// Mengimpor model yang diperlukan untuk Buku, Kategori, Penulis, dan Penerbit
import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.model.Penerbit

// Mengimpor BukuService untuk komunikasi dengan API
import com.example.projectakhir.data.service.BukuService

// Mengimpor Response dari Retrofit dan IOException untuk menangani error
import retrofit2.Response
import java.io.IOException

// Define Repository Interface for Buku
// BukuRepository adalah antarmuka yang mendefinisikan operasi CRUD pada Buku
interface BukuRepository {
    suspend fun getBuku(): List<Buku> // Fungsi untuk mendapatkan daftar semua Buku
    suspend fun getBukuById(idBuku: Int): Buku // Fungsi untuk mendapatkan detail Buku berdasarkan ID
    suspend fun insertBuku(buku: Buku) // Fungsi untuk menambahkan Buku baru
    suspend fun updateBuku(idBuku: Int, buku: Buku) // Fungsi untuk memperbarui Buku berdasarkan ID
    suspend fun deleteBuku(idBuku: Int) // Fungsi untuk menghapus Buku berdasarkan ID
}

// Implementasi BukuRepository menggunakan Network (API)
class NetworkBukuRepository(private val bukuService: BukuService) : BukuRepository {

    // Implementasi fungsi untuk mendapatkan semua Buku
    override suspend fun getBuku(): List<Buku> {
        return try {
            // Memanggil API untuk mendapatkan data Buku
            val response = bukuService.getBuku()
            // Mengecek apakah respons berhasil, jika iya kembalikan data, jika tidak, lempar IOException
            if (response.isSuccessful) response.body() ?: emptyList()
            else throw IOException("Failed to fetch data: ${response.code()} - ${response.message()}")
        } catch (e: Exception) {
            // Menangkap error lain dan melempar IOException dengan pesan error
            throw IOException("Error fetching Buku data: ${e.message}")
        }
    }

    // Implementasi fungsi untuk mendapatkan Buku berdasarkan ID
    override suspend fun getBukuById(idBuku: Int): Buku {
        return try {
            // Memanggil API untuk mendapatkan Buku berdasarkan ID
            val response = bukuService.getBukuById(idBuku)
            // Mengecek apakah respons berhasil, jika iya kembalikan data Buku, jika tidak, lempar IOException
            if (response.isSuccessful) response.body()!!
            else throw IOException("Failed to fetch Buku: ${response.code()} - ${response.message()}")
        } catch (e: Exception) {
            // Menangkap error lain dan melempar IOException dengan pesan error
            throw IOException("Error fetching Buku by ID: ${e.message}")
        }
    }

    // Implementasi fungsi untuk menambahkan Buku baru
    override suspend fun insertBuku(buku: Buku) {
        try {
            // Memanggil API untuk menambahkan Buku baru
            val response = bukuService.insertBuku(buku)
            // Mengecek apakah respons berhasil, jika tidak, ambil errorBody dan lempar IOException
            if (!response.isSuccessful) {
                val errorBody = response.errorBody()?.string()
                throw IOException("Failed to insert Buku: ${response.code()} - $errorBody")
            }
        } catch (e: Exception) {
            // Menangkap error lain dan melempar IOException dengan pesan error
            throw IOException("Error inserting Buku: ${e.message}")
        }
    }

    // Implementasi fungsi untuk memperbarui Buku berdasarkan ID
    override suspend fun updateBuku(idBuku: Int, buku: Buku) {
        try {
            // Memanggil API untuk memperbarui Buku berdasarkan ID
            val response = bukuService.updateBuku(idBuku, buku)
            // Mengecek apakah respons berhasil, jika tidak, lempar IOException
            if (!response.isSuccessful) {
                throw IOException("Failed to update Buku: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            // Menangkap error lain dan melempar IOException dengan pesan error
            throw IOException("Error updating Buku: ${e.message}")
        }
    }

    // Implementasi fungsi untuk menghapus Buku berdasarkan ID
    override suspend fun deleteBuku(idBuku: Int) {
        try {
            // Memanggil API untuk menghapus Buku berdasarkan ID
            val response = bukuService.deleteBuku(idBuku)
            // Mengecek apakah respons berhasil, jika tidak, lempar IOException
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Buku: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            // Menangkap error lain dan melempar IOException dengan pesan error
            throw IOException("Error deleting Buku: ${e.message}")
        }
    }
}
