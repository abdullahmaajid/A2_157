package com.example.projectakhir.data.service



import com.example.projectakhir.data.model.Kategori
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KategoriService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("api/kategori")
    suspend fun getKategori(): List<Kategori>

    @GET("api/kategori/{id}")
    suspend fun getKategoriById(@Path("id") idKategori: Int): Kategori

    @POST("api/kategori")
    suspend fun insertKategori(@Body kategori: Kategori)

    @PUT("api/kategori/{id}")
    suspend fun updateKategori(@Path("id") idKategori: Int, @Body kategori: Kategori)

    @DELETE("api/kategori/{id}")
    suspend fun deleteKategori(@Path("id") idKategori: Int): Response<Void>
}