package com.example.projectakhir.data.repository



import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.service.BukuService

interface BukuRepository {
    suspend fun getBuku(): List<Buku>
    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(idBuku: Int, buku: Buku)
    suspend fun deleteBuku(idBuku: Int)
    suspend fun getBukuById(idBuku: Int): Buku
}

class NetworkBukuRepository(private val bukuService: BukuService) : BukuRepository {
    override suspend fun getBuku(): List<Buku> = bukuService.getBuku()

    override suspend fun insertBuku(buku: Buku) {
        bukuService.insertBuku(buku)
    }

    override suspend fun updateBuku(idBuku: Int, buku: Buku) {
        bukuService.updateBuku(idBuku, buku)
    }

    override suspend fun deleteBuku(idBuku: Int) {
        try {
            val response = bukuService.deleteBuku(idBuku)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete buku. HTTP Status Code: ${response.code()}")
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getBukuById(idBuku: Int): Buku {
        return bukuService.getBukuById(idBuku)
    }
}