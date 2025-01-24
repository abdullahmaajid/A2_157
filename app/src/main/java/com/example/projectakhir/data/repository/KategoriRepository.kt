package com.example.projectakhir.data.repository



import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.service.KategoriService

interface KategoriRepository {
    suspend fun getKategori(): List<Kategori>
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(idKategori: Int, kategori: Kategori)
    suspend fun deleteKategori(idKategori: Int)
    suspend fun getKategoriById(idKategori: Int): Kategori
}

class NetworkKategoriRepository(private val kategoriService: KategoriService) : KategoriRepository {
    override suspend fun getKategori(): List<Kategori> = kategoriService.getKategori()

    override suspend fun insertKategori(kategori: Kategori) {
        kategoriService.insertKategori(kategori)
    }

    override suspend fun updateKategori(idKategori: Int, kategori: Kategori) {
        kategoriService.updateKategori(idKategori, kategori)
    }

    override suspend fun deleteKategori(idKategori: Int) {
        try {
            val response = kategoriService.deleteKategori(idKategori)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete kategori. HTTP Status Code: ${response.code()}")
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKategoriById(idKategori: Int): Kategori {
        return kategoriService.getKategoriById(idKategori)
    }
}