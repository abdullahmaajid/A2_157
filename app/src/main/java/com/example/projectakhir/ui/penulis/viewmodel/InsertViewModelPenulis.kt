package com.example.projectakhir.ui.penulis.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.repository.PenulisRepository
import kotlinx.coroutines.launch

// ViewModel for inserting a Penulis
class InsertViewModelPenulis(
    private val savedStateHandle: SavedStateHandle,
    private val penulisRepository: PenulisRepository
) : ViewModel() {

    // Define the UI state variable for Insert operation
    var uiState by mutableStateOf(PenulisUiState1())
        private set

    // Update the UI state with the new Penulis event
    fun updateInsertPenulisState(penulisUiEvent: PenulisUiEvent) {
        uiState = PenulisUiState1(penulisUiEvent = penulisUiEvent)
    }

    // Insert the Penulis by calling the repository
    fun insertPenulis() {
        viewModelScope.launch {
            try {
                // Call the insertPenulis method on the actual PenulisRepository
                penulisRepository.insertPenulis(uiState.penulisUiEvent.toPenulis())
            } catch (e: Exception) {
                // Handle the error (e.g., log or update the UI state with an error message)
                e.printStackTrace()
            }
        }
    }
}

// Define the state class for the UI state (Insert operation)
data class PenulisUiState1(
    val penulisUiEvent: PenulisUiEvent = PenulisUiEvent(),
    val error: String? = null
)

// Define the event class representing a Penulis
data class PenulisUiEvent(
    val idPenulis: Int = 0,
    val namaPenulis: String = "",
    val biografi: String = "",
    val kontak: String = ""
)

// Extension function to convert PenulisUiEvent to Penulis (Model class)
fun PenulisUiEvent.toPenulis(): Penulis = Penulis(
    idPenulis = idPenulis,
    namaPenulis = namaPenulis,
    biografi = biografi,
    kontak = kontak
)

// Extension function to convert Penulis model to PenulisUiEvent
fun Penulis.toUiStatePenulis(): PenulisUiEvent = PenulisUiEvent(
    idPenulis = idPenulis,
    namaPenulis = namaPenulis,
    biografi = biografi,
    kontak = kontak
)
