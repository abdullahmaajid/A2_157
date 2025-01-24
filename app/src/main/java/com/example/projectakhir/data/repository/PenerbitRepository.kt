package com.example.projectakhir.data.repository


import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.service.PenerbitService

interface PenerbitRepository {
    suspend fun getPenerbit(): List<Penerbit>
    suspend fun insertPenerbit(penerbit: Penerbit)
    suspend fun updatePenerbit(idPenerbit: Int, penerbit: Penerbit)
    suspend fun deletePenerbit(idPenerbit: Int)
    suspend fun getPenerbitById(idPenerbit: Int): Penerbit
}

class NetworkPenerbitRepository(private val penerbitService: PenerbitService) : PenerbitRepository {
    override suspend fun getPenerbit(): List<Penerbit> = penerbitService.getPenerbit()

    override suspend fun insertPenerbit(penerbit: Penerbit) {
        penerbitService.insertPenerbit(penerbit)
    }

    override suspend fun updatePenerbit(idPenerbit: Int, penerbit: Penerbit) {
        penerbitService.updatePenerbit(idPenerbit, penerbit)
    }

    override suspend fun deletePenerbit(idPenerbit: Int) {
        try {
            val response = penerbitService.deletePenerbit(idPenerbit)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete penerbit. HTTP Status Code: ${response.code()}")
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPenerbitById(idPenerbit: Int): Penerbit {
        return penerbitService.getPenerbitById(idPenerbit)
    }
}