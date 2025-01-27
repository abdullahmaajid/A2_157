package com.example.projectakhir.data.repository




import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.service.BukuService
import retrofit2.Response
import java.io.IOException

// Define the repository interface
// Define Repository Interface for Buku
interface BukuRepository {
    suspend fun getBuku(): List<Buku>
    suspend fun getBukuById(idBuku: Int): Buku
    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(idBuku: Int, buku: Buku)
    suspend fun deleteBuku(idBuku: Int)
}

// Implementation of BukuRepository
class NetworkBukuRepository(private val bukuService: BukuService) : BukuRepository {
    override suspend fun getBuku(): List<Buku> {
        return try {
            val response = bukuService.getBuku()
            if (response.isSuccessful) response.body() ?: emptyList()
            else throw IOException("Failed to fetch data: ${response.code()} - ${response.message()}")
        } catch (e: Exception) {
            throw IOException("Error fetching Buku data: ${e.message}")
        }
    }

    override suspend fun getBukuById(idBuku: Int): Buku {
        return try {
            val response = bukuService.getBukuById(idBuku)
            if (response.isSuccessful) response.body()!!
            else throw IOException("Failed to fetch Buku: ${response.code()} - ${response.message()}")
        } catch (e: Exception) {
            throw IOException("Error fetching Buku by ID: ${e.message}")
        }
    }

    override suspend fun insertBuku(buku: Buku) {
        try {
            val response = bukuService.insertBuku(buku)
            if (!response.isSuccessful) {
                val errorBody = response.errorBody()?.string()
                throw IOException("Failed to insert Buku: ${response.code()} - $errorBody")
            }
        } catch (e: Exception) {
            throw IOException("Error inserting Buku: ${e.message}")
        }
    }

    override suspend fun updateBuku(idBuku: Int, buku: Buku) {
        try {
            val response = bukuService.updateBuku(idBuku, buku)
            if (!response.isSuccessful) {
                throw IOException("Failed to update Buku: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            throw IOException("Error updating Buku: ${e.message}")
        }
    }

    override suspend fun deleteBuku(idBuku: Int) {
        try {
            val response = bukuService.deleteBuku(idBuku)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Buku: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            throw IOException("Error deleting Buku: ${e.message}")
        }
    }
}




