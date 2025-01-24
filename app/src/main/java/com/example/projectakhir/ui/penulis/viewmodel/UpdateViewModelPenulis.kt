package com.example.projectakhir.ui.penulis.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.repository.PenulisRepository
import com.example.projectakhir.ui.navigasi.DestinasiUpdatePenulis
import kotlinx.coroutines.launch

class UpdateViewModelPenulis(
    savedStateHandle: SavedStateHandle,
    private val pnls: PenulisRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(PenulisUiState1())
        private set

    private val _idPenulis: Int = checkNotNull(savedStateHandle[DestinasiUpdatePenulis.idPenulisArg])

    init {
        viewModelScope.launch {
            try {
                val penulis = pnls.getPenulisById(_idPenulis)
                updateUiState = PenulisUiState1(penulis.toUiStatePenulis())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = PenulisUiState1(error = "Failed to load penulis.")
            }
        }
    }

    fun updateInsertPenulisState(penulisUiEvent: PenulisUiEvent) {
        updateUiState = PenulisUiState1(penulisUiEvent = penulisUiEvent)
    }

    fun updatePenulis() {
        viewModelScope.launch {
            try {
                pnls.updatePenulis(_idPenulis, updateUiState.penulisUiEvent.toPenulis())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = updateUiState.copy(error = "Failed to update penulis.")
            }
        }
    }
}
