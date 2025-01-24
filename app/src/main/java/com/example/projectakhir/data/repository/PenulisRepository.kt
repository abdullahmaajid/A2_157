package com.example.projectakhir.data.repository


import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.service.PenulisService

interface PenulisRepository {
    suspend fun getPenulis(): List<Penulis>
    suspend fun insertPenulis(penulis: Penulis)
    suspend fun updatePenulis(idPenulis: Int, penulis: Penulis)
    suspend fun deletePenulis(idPenulis: Int)
    suspend fun getPenulisById(idPenulis: Int): Penulis
}

class NetworkPenulisRepository(private val penulisService: PenulisService) : PenulisRepository {
    override suspend fun getPenulis(): List<Penulis> = penulisService.getPenulis()

    override suspend fun insertPenulis(penulis: Penulis) {
        penulisService.insertPenulis(penulis)
    }

    override suspend fun updatePenulis(idPenulis: Int, penulis: Penulis) {
        penulisService.updatePenulis(idPenulis, penulis)
    }

    override suspend fun deletePenulis(idPenulis: Int) {
        try {
            val response = penulisService.deletePenulis(idPenulis)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete penulis. HTTP Status Code: ${response.code()}")
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPenulisById(idPenulis: Int): Penulis {
        return penulisService.getPenulisById(idPenulis)
    }
}