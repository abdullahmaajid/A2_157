// Package deklarasi untuk lokasi file ini
package com.example.projectakhir.data.service

// Import model yang diperlukan untuk penulis
import com.example.projectakhir.data.model.Penulis

// Mengimport library Retrofit untuk membuat request HTTP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Interface ini mendefinisikan API endpoints untuk interaksi dengan data Penulis
interface PenulisService {

    // Menambahkan header ke setiap permintaan API untuk menetapkan jenis konten dan format respons
    @Headers(
        "Accept: application/json",  // Memberitahu server bahwa kita menerima data dalam format JSON
        "Content-Type: application/json",  // Memberitahu server bahwa kita mengirim data dalam format JSON
    )

    // Endpoint untuk mendapatkan daftar Penulis
    @GET("api/penulis")
    suspend fun getPenulis(): List<Penulis>  // Fungsi ini akan mengembalikan daftar Penulis

    // Endpoint untuk mendapatkan Penulis berdasarkan ID
    @GET("api/penulis/{id}")
    suspend fun getPenulisById(@Path("id") idPenulis: Int): Penulis  // Fungsi ini akan mengembalikan satu Penulis berdasarkan ID

    // Endpoint untuk menambahkan Penulis baru
    @POST("api/penulis")
    suspend fun insertPenulis(@Body penulis: Penulis)  // Fungsi ini akan mengirimkan data Penulis baru ke server untuk ditambahkan

    // Endpoint untuk memperbarui data Penulis berdasarkan ID
    @PUT("api/penulis/{id}")
    suspend fun updatePenulis(@Path("id") idPenulis: Int, @Body penulis: Penulis)  // Fungsi ini akan memperbarui Penulis yang ada berdasarkan ID

    // Endpoint untuk menghapus Penulis berdasarkan ID
    @DELETE("api/penulis/{id}")
    suspend fun deletePenulis(@Path("id") idPenulis: Int): Response<Void>  // Fungsi ini akan menghapus Penulis berdasarkan ID yang diberikan
}
