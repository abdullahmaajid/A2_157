package com.example.projectakhir.ui.kategori.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.repository.KategoriRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// State untuk UI
sealed class KategoriUiState {
    data class Success(val kategori: List<Kategori>) : KategoriUiState()
    object Error : KategoriUiState()
    object Loading : KategoriUiState()
}

class HomeViewModelKategori(private val kategoriRepository: KategoriRepository) : ViewModel() {

    var kategoriUiState: KategoriUiState by mutableStateOf(KategoriUiState.Loading)
        private set

    init {
        getKategori()
    }

    // Mendapatkan semua data kategori
    fun getKategori() {
        viewModelScope.launch {
            kategoriUiState = KategoriUiState.Loading
            try {
                val kategoriList = kategoriRepository.getKategori()
                kategoriUiState = KategoriUiState.Success(kategoriList)
            } catch (e: IOException) {
                kategoriUiState = KategoriUiState.Error
            } catch (e: HttpException) {
                kategoriUiState = KategoriUiState.Error
            }
        }
    }

    // Mendapatkan kategori berdasarkan ID
    fun getKategoriById(idKategori: Int, onSuccess: (Kategori) -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val kategori = kategoriRepository.getKategoriById(idKategori)
                onSuccess(kategori)
            } catch (e: IOException) {
                onError()
            } catch (e: HttpException) {
                onError()
            }
        }
    }

    // Menghapus data kategori berdasarkan ID
    fun deleteKategori(idKategori: Int) {
        viewModelScope.launch {
            try {
                kategoriRepository.deleteKategori(idKategori)
                // Refresh data kategori setelah menghapus
                getKategori()
            } catch (e: IOException) {
                kategoriUiState = KategoriUiState.Error
            } catch (e: HttpException) {
                kategoriUiState = KategoriUiState.Error
            }
        }
    }

    // Menambahkan kategori baru
    fun insertKategori(kategori: Kategori) {
        viewModelScope.launch {
            try {
                kategoriRepository.insertKategori(kategori)
                // Refresh data kategori setelah menambah
                getKategori()
            } catch (e: IOException) {
                kategoriUiState = KategoriUiState.Error
            } catch (e: HttpException) {
                kategoriUiState = KategoriUiState.Error
            }
        }
    }

    // Mengupdate data kategori berdasarkan ID
    fun updateKategori(idKategori: Int, kategori: Kategori) {
        viewModelScope.launch {
            try {
                kategoriRepository.updateKategori(idKategori, kategori)
                // Refresh data kategori setelah mengupdate
                getKategori()
            } catch (e: IOException) {
                kategoriUiState = KategoriUiState.Error
            } catch (e: HttpException) {
                kategoriUiState = KategoriUiState.Error
            }
        }
    }
}