package com.example.projectakhir.data.service


import com.example.projectakhir.data.model.Buku
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BukuService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("api/buku")
    suspend fun getBuku(): List<Buku>

    @GET("api/buku/{id}")
    suspend fun getBukuById(@Path("id") idBuku: Int): Buku

    @POST("api/buku")
    suspend fun insertBuku(@Body buku: Buku)

    @PUT("api/buku/{id}")
    suspend fun updateBuku(@Path("id") idBuku: Int, @Body buku: Buku)

    @DELETE("api/buku/{id}")
    suspend fun deleteBuku(@Path("id") idBuku: Int): Response<Void>
}