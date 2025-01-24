package com.example.projectakhir.ui.buku.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.repository.BukuRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// State untuk UI
sealed class BukuUiState {
    data class Success(val buku: List<Buku>) : BukuUiState()
    object Error : BukuUiState()
    object Loading : BukuUiState()
}

class HomeViewModelBuku(private val bukuRepository: BukuRepository) : ViewModel() {

    var bukuUiState: BukuUiState by mutableStateOf(BukuUiState.Loading)
        private set

    init {
        getBuku()
    }

    // Mendapatkan semua data buku
    fun getBuku() {
        viewModelScope.launch {
            bukuUiState = BukuUiState.Loading
            try {
                val bukuList = bukuRepository.getBuku()
                bukuUiState = BukuUiState.Success(bukuList)
            } catch (e: IOException) {
                bukuUiState = BukuUiState.Error
            } catch (e: HttpException) {
                bukuUiState = BukuUiState.Error
            }
        }
    }

    // Menghapus data buku berdasarkan ID
    fun deleteBuku(idBuku: Int) {
        viewModelScope.launch {
            try {
                bukuRepository.deleteBuku(idBuku)
                // Refresh data buku setelah menghapus
                getBuku()
            } catch (e: IOException) {
                bukuUiState = BukuUiState.Error
            } catch (e: HttpException) {
                bukuUiState = BukuUiState.Error
            }
        }
    }

    // Menambahkan buku baru
    fun insertBuku(buku: Buku) {
        viewModelScope.launch {
            try {
                bukuRepository.insertBuku(buku)
                // Refresh data buku setelah menambah
                getBuku()
            } catch (e: IOException) {
                bukuUiState = BukuUiState.Error
            } catch (e: HttpException) {
                bukuUiState = BukuUiState.Error
            }
        }
    }

    // Mengupdate data buku berdasarkan ID
    fun updateBuku(idBuku: Int, buku: Buku) {
        viewModelScope.launch {
            try {
                bukuRepository.updateBuku(idBuku, buku)
                // Refresh data buku setelah mengupdate
                getBuku()
            } catch (e: IOException) {
                bukuUiState = BukuUiState.Error
            } catch (e: HttpException) {
                bukuUiState = BukuUiState.Error
            }
        }
    }
}
