package com.example.projectakhir.ui.buku.viewmodel


import android.util.Log
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

sealed class HomeUiState {
    data class Success(val buku: List<Buku>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModelBuku(private val bukuRepository: BukuRepository ) : ViewModel() {

    var homeUiState : HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set
    init {
        Log.d("HomeViewModelBuku", "ViewModel diinisialisasi, memuat data...")
        getBuku()
    }
    fun getBuku() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            try {
                val bukuList = bukuRepository.getBuku()
                homeUiState = HomeUiState.Success(bukuList)
            } catch (e:IOException) {
                homeUiState = HomeUiState.Error
            } catch (e:HttpException) {
                homeUiState = HomeUiState.Error
            }
        }
    }

    fun deleteBuku(idBuku: Int) {
        viewModelScope.launch {
            try {
                bukuRepository.deleteBuku(idBuku)
                getBuku()
            } catch (e: IOException) {
                homeUiState = HomeUiState.Error
            } catch (e: HttpException) {
                homeUiState = HomeUiState.Error
            }
        }
    }

}