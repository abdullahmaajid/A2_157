package com.example.projectakhir.ui.kategori.viewmodel



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.repository.KategoriRepository
import com.example.projectakhir.ui.navigasi.DestinasiUpdateKategori
import kotlinx.coroutines.launch

class UpdateViewModelKategori(
    savedStateHandle: SavedStateHandle,
    private val ktgr: KategoriRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(KategoriUiState1())
        private set

    private val _idKategori: Int = checkNotNull(savedStateHandle[DestinasiUpdateKategori.idKategoriArg])

    init {
        viewModelScope.launch {
            try {
                val kategori = ktgr.getKategoriById(_idKategori)
                updateUiState = KategoriUiState1(kategori.toUiStateKtgr())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = KategoriUiState1(error = "Failed to load category.")
            }
        }
    }

    fun updateInsertKtgrState(kategoriUiEvent: KategoriUiEvent) {
        updateUiState = KategoriUiState1(kategoriUiEvent = kategoriUiEvent)
    }

    fun updateKategori() {
        viewModelScope.launch {
            try {
                ktgr.updateKategori(_idKategori, updateUiState.kategoriUiEvent.toKtgr())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = updateUiState.copy(error = "Failed to update category.")
            }
        }
    }
}

