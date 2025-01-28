// Package deklarasi untuk lokasi file ini
package com.example.projectakhir.data.service

// Import model yang diperlukan untuk kategori
import com.example.projectakhir.data.model.Kategori

// Mengimport library Retrofit untuk membuat request HTTP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Interface ini mendefinisikan API endpoints untuk interaksi dengan data Kategori
interface KategoriService {

    // Menambahkan header ke setiap permintaan API untuk menetapkan jenis konten dan format respons
    @Headers(
        "Accept: application/json",  // Memberitahu server bahwa kita menerima data dalam format JSON
        "Content-Type: application/json",  // Memberitahu server bahwa kita mengirim data dalam format JSON
    )

    // Endpoint untuk mendapatkan daftar Kategori
    @GET("api/kategori")
    suspend fun getKategori(): List<Kategori>  // Fungsi ini akan mengembalikan daftar Kategori

    // Endpoint untuk mendapatkan Kategori berdasarkan ID
    @GET("api/kategori/{id}")
    suspend fun getKategoriById(@Path("id") idKategori: Int): Kategori  // Fungsi ini akan mengembalikan satu Kategori berdasarkan ID

    // Endpoint untuk menambahkan Kategori baru
    @POST("api/kategori")
    suspend fun insertKategori(@Body kategori: Kategori)  // Fungsi ini akan mengirimkan data Kategori baru ke server untuk ditambahkan

    // Endpoint untuk memperbarui data Kategori berdasarkan ID
    @PUT("api/kategori/{id}")
    suspend fun updateKategori(@Path("id") idKategori: Int, @Body kategori: Kategori)  // Fungsi ini akan memperbarui Kategori yang ada berdasarkan ID

    // Endpoint untuk menghapus Kategori berdasarkan ID
    @DELETE("api/kategori/{id}")
    suspend fun deleteKategori(@Path("id") idKategori: Int): Response<Void>  // Fungsi ini akan menghapus Kategori berdasarkan ID yang diberikan
}
