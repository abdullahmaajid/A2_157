package com.example.projectakhir.data.service



import com.example.projectakhir.data.model.Penulis
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PenulisService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("api/penulis")
    suspend fun getPenulis(): List<Penulis>

    @GET("api/penulis/{id}")
    suspend fun getPenulisById(@Path("id") idPenulis: Int): Penulis

    @POST("api/penulis")
    suspend fun insertPenulis(@Body penulis: Penulis)

    @PUT("api/penulis/{id}")
    suspend fun updatePenulis(@Path("id") idPenulis: Int, @Body penulis: Penulis)

    @DELETE("api/penulis/{id}")
    suspend fun deletePenulis(@Path("id") idPenulis: Int): Response<Void>
}