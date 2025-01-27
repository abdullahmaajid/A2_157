package com.example.projectakhir.data.service




import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.model.Penerbit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BukuService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    // Buku Endpoints
    @GET("api/buku")
    suspend fun getBuku(): Response<List<Buku>>

    @GET("api/buku/{id}")
    suspend fun getBukuById(@Path("id") idBuku: Int): Response<Buku>

    @POST("api/buku")
    suspend fun insertBuku(@Body buku: Buku): Response<Buku>

    @PUT("api/buku/{id}")
    suspend fun updateBuku(@Path("id") idBuku: Int, @Body buku: Buku): Response<Buku>

    @DELETE("api/buku/{id}")
    suspend fun deleteBuku(@Path("id") idBuku: Int): Response<Void>


}

