package com.example.projectakhir.ui.penerbit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.repository.PenerbitRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// State untuk UI
sealed class PenerbitUiState {
    data class Success(val penerbit: List<Penerbit>) : PenerbitUiState()
    object Error : PenerbitUiState()
    object Loading : PenerbitUiState()
}

class HomeViewModelPenerbit(private val penerbitRepository: PenerbitRepository) : ViewModel() {

    var penerbitUiState: PenerbitUiState by mutableStateOf(PenerbitUiState.Loading)
        private set

    init {
        getPenerbit()
    }

    // Mendapatkan semua data penerbit
    fun getPenerbit() {
        viewModelScope.launch {
            penerbitUiState = PenerbitUiState.Loading
            try {
                val penerbitList = penerbitRepository.getPenerbit()
                penerbitUiState = PenerbitUiState.Success(penerbitList)
            } catch (e: IOException) {
                penerbitUiState = PenerbitUiState.Error
            } catch (e: HttpException) {
                penerbitUiState = PenerbitUiState.Error
            }
        }
    }

    // Mendapatkan penerbit berdasarkan ID
    fun getPenerbitById(idPenerbit: Int, onSuccess: (Penerbit) -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val penerbit = penerbitRepository.getPenerbitById(idPenerbit)
                onSuccess(penerbit)
            } catch (e: IOException) {
                onError()
            } catch (e: HttpException) {
                onError()
            }
        }
    }

    // Menghapus data penerbit berdasarkan ID
    fun deletePenerbit(idPenerbit: Int) {
        viewModelScope.launch {
            try {
                penerbitRepository.deletePenerbit(idPenerbit)
                // Refresh data penerbit setelah menghapus
                getPenerbit()
            } catch (e: IOException) {
                penerbitUiState = PenerbitUiState.Error
            } catch (e: HttpException) {
                penerbitUiState = PenerbitUiState.Error
            }
        }
    }

    // Menambahkan penerbit baru
    fun insertPenerbit(penerbit: Penerbit) {
        viewModelScope.launch {
            try {
                penerbitRepository.insertPenerbit(penerbit)
                // Refresh data penerbit setelah menambah
                getPenerbit()
            } catch (e: IOException) {
                penerbitUiState = PenerbitUiState.Error
            } catch (e: HttpException) {
                penerbitUiState = PenerbitUiState.Error
            }
        }
    }

    // Mengupdate data penerbit berdasarkan ID
    fun updatePenerbit(idPenerbit: Int, penerbit: Penerbit) {
        viewModelScope.launch {
            try {
                penerbitRepository.updatePenerbit(idPenerbit, penerbit)
                // Refresh data penerbit setelah mengupdate
                getPenerbit()
            } catch (e: IOException) {
                penerbitUiState = PenerbitUiState.Error
            } catch (e: HttpException) {
                penerbitUiState = PenerbitUiState.Error
            }
        }
    }
}
