package com.example.projectakhir.data.service



import com.example.projectakhir.data.model.Penerbit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PenerbitService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("api/penerbit")
    suspend fun getPenerbit(): List<Penerbit>

    @GET("api/penerbit/{id}")
    suspend fun getPenerbitById(@Path("id") idPenerbit: Int): Penerbit

    @POST("api/penerbit")
    suspend fun insertPenerbit(@Body penerbit: Penerbit)

    @PUT("api/penerbit/{id}")
    suspend fun updatePenerbit(@Path("id") idPenerbit: Int, @Body penerbit: Penerbit)

    @DELETE("api/penerbit/{id}")
    suspend fun deletePenerbit(@Path("id") idPenerbit: Int): Response<Void>
}