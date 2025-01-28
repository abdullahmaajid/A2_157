// Package deklarasi untuk lokasi file ini
package com.example.projectakhir.data.service

// Import model yang diperlukan untuk penerbit
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

// Interface ini mendefinisikan API endpoints untuk interaksi dengan data Penerbit
interface PenerbitService {

    // Menambahkan header ke setiap permintaan API untuk menetapkan jenis konten dan format respons
    @Headers(
        "Accept: application/json",  // Memberitahu server bahwa kita menerima data dalam format JSON
        "Content-Type: application/json",  // Memberitahu server bahwa kita mengirim data dalam format JSON
    )

    // Endpoint untuk mendapatkan daftar Penerbit
    @GET("api/penerbit")
    suspend fun getPenerbit(): List<Penerbit>  // Fungsi ini akan mengembalikan daftar Penerbit

    // Endpoint untuk mendapatkan Penerbit berdasarkan ID
    @GET("api/penerbit/{id}")
    suspend fun getPenerbitById(@Path("id") idPenerbit: Int): Penerbit  // Fungsi ini akan mengembalikan satu Penerbit berdasarkan ID

    // Endpoint untuk menambahkan Penerbit baru
    @POST("api/penerbit")
    suspend fun insertPenerbit(@Body penerbit: Penerbit)  // Fungsi ini akan mengirimkan data Penerbit baru ke server untuk ditambahkan

    // Endpoint untuk memperbarui data Penerbit berdasarkan ID
    @PUT("api/penerbit/{id}")
    suspend fun updatePenerbit(@Path("id") idPenerbit: Int, @Body penerbit: Penerbit)  // Fungsi ini akan memperbarui Penerbit yang ada berdasarkan ID

    // Endpoint untuk menghapus Penerbit berdasarkan ID
    @DELETE("api/penerbit/{id}")
    suspend fun deletePenerbit(@Path("id") idPenerbit: Int): Response<Void>  // Fungsi ini akan menghapus Penerbit berdasarkan ID yang diberikan
}
