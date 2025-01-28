// Package deklarasi untuk lokasi file ini
package com.example.projectakhir.data.service

// Import model yang diperlukan untuk buku, kategori, penulis, dan penerbit
import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.model.Penerbit

// Mengimport library Retrofit untuk membuat request HTTP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Interface ini mendefinisikan API endpoints untuk interaksi dengan data Buku
interface BukuService {

    // Menambahkan header ke setiap permintaan API untuk menetapkan jenis konten dan format respons
    @Headers(
        "Accept: application/json",  // Memberitahu server bahwa kita menerima data dalam format JSON
        "Content-Type: application/json",  // Memberitahu server bahwa kita mengirim data dalam format JSON
    )

    // Endpoint untuk mendapatkan daftar Buku
    @GET("api/buku")
    suspend fun getBuku(): Response<List<Buku>>  // Fungsi ini akan mengembalikan Response berisi daftar Buku

    // Endpoint untuk mendapatkan Buku berdasarkan ID
    @GET("api/buku/{id}")
    suspend fun getBukuById(@Path("id") idBuku: Int): Response<Buku>  // Fungsi ini akan mengembalikan satu Buku berdasarkan ID

    // Endpoint untuk menambahkan Buku baru
    @POST("api/buku")
    suspend fun insertBuku(@Body buku: Buku): Response<Buku>  // Fungsi ini akan mengirim data Buku baru untuk ditambahkan ke database

    // Endpoint untuk memperbarui data Buku berdasarkan ID
    @PUT("api/buku/{id}")
    suspend fun updateBuku(@Path("id") idBuku: Int, @Body buku: Buku): Response<Buku>  // Fungsi ini akan memperbarui Buku yang ada

    // Endpoint untuk menghapus Buku berdasarkan ID
    @DELETE("api/buku/{id}")
    suspend fun deleteBuku(@Path("id") idBuku: Int): Response<Void>  // Fungsi ini akan menghapus Buku berdasarkan ID
}
