package com.example.projectakhir.ui.penulis.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.repository.PenulisRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// State untuk UI
sealed class PenulisUiState {
    data class Success(val penulis: List<Penulis>) : PenulisUiState()
    object Error : PenulisUiState()
    object Loading : PenulisUiState()
}

class HomeViewModelPenulis(private val penulisRepository: PenulisRepository) : ViewModel() {

    var penulisUiState: PenulisUiState by mutableStateOf(PenulisUiState.Loading)
        private set

    init {
        getPenulis()
    }

    // Mendapatkan semua data penulis
    fun getPenulis() {
        viewModelScope.launch {
            penulisUiState = PenulisUiState.Loading
            try {
                val penulisList = penulisRepository.getPenulis()
                penulisUiState = PenulisUiState.Success(penulisList)
            } catch (e: IOException) {
                penulisUiState = PenulisUiState.Error
            } catch (e: HttpException) {
                penulisUiState = PenulisUiState.Error
            }
        }
    }

    // Mendapatkan penulis berdasarkan ID
    fun getPenulisById(idPenulis: Int, onSuccess: (Penulis) -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val penulis = penulisRepository.getPenulisById(idPenulis)
                onSuccess(penulis)
            } catch (e: IOException) {
                onError()
            } catch (e: HttpException) {
                onError()
            }
        }
    }

    // Menghapus data penulis berdasarkan ID
    fun deletePenulis(idPenulis: Int) {
        viewModelScope.launch {
            try {
                penulisRepository.deletePenulis(idPenulis)
                // Refresh data penulis setelah menghapus
                getPenulis()
            } catch (e: IOException) {
                penulisUiState = PenulisUiState.Error
            } catch (e: HttpException) {
                penulisUiState = PenulisUiState.Error
            }
        }
    }

    // Menambahkan penulis baru
    fun insertPenulis(penulis: Penulis) {
        viewModelScope.launch {
            try {
                penulisRepository.insertPenulis(penulis)
                // Refresh data penulis setelah menambah
                getPenulis()
            } catch (e: IOException) {
                penulisUiState = PenulisUiState.Error
            } catch (e: HttpException) {
                penulisUiState = PenulisUiState.Error
            }
        }
    }

    // Mengupdate data penulis berdasarkan ID
    fun updatePenulis(idPenulis: Int, penulis: Penulis) {
        viewModelScope.launch {
            try {
                penulisRepository.updatePenulis(idPenulis, penulis)
                // Refresh data penulis setelah mengupdate
                getPenulis()
            } catch (e: IOException) {
                penulisUiState = PenulisUiState.Error
            } catch (e: HttpException) {
                penulisUiState = PenulisUiState.Error
            }
        }
    }
}
