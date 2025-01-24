package com.example.projectakhir.ui.penerbit.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.repository.PenerbitRepository
import com.example.projectakhir.ui.navigasi.DestinasiUpdatePenerbit
import kotlinx.coroutines.launch

class UpdateViewModelPenerbit(
    savedStateHandle: SavedStateHandle,
    private val pnrbt: PenerbitRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(PenerbitUiState1())
        private set

    private val _idPenerbit: Int = checkNotNull(savedStateHandle[DestinasiUpdatePenerbit.idPenerbitArg])

    init {
        viewModelScope.launch {
            try {
                val penerbit = pnrbt.getPenerbitById(_idPenerbit)
                updateUiState = PenerbitUiState1(penerbit.toUiStatePnrbt())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = PenerbitUiState1(error = "Failed to load publisher.")
            }
        }
    }

    fun updateInsertPnrbtState(penerbitUiEvent: PenerbitUiEvent) {
        updateUiState = PenerbitUiState1(penerbitUiEvent = penerbitUiEvent)
    }

    fun updatePenerbit() {
        viewModelScope.launch {
            try {
                pnrbt.updatePenerbit(_idPenerbit, updateUiState.penerbitUiEvent.toPnrbt())
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState = updateUiState.copy(error = "Failed to update publisher.")
            }
        }
    }
}
