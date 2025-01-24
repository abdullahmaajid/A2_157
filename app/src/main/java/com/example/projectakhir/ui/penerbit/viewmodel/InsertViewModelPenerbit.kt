package com.example.projectakhir.ui.penerbit.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.repository.KategoriRepository
import com.example.projectakhir.data.repository.PenerbitRepository
import com.example.projectakhir.ui.kategori.viewmodel.KategoriUiEvent
import com.example.projectakhir.ui.kategori.viewmodel.KategoriUiState1
import com.example.projectakhir.ui.kategori.viewmodel.toKtgr
import kotlinx.coroutines.launch

// ViewModel for inserting a publisher (Penerbit)
class InsertViewModelPenerbit(
    //private val penerbitRepository: PenerbitRepository
    penerbitRepository: SavedStateHandle,
    private val pnrbt: PenerbitRepository
) : ViewModel() {
    // Define the UI state variable for Insert operation
    var uiState by mutableStateOf(PenerbitUiState1())
        private set

    // Update the UI state with the new publisher event
    fun updateInsertPnrbtState(penerbitUiEvent: PenerbitUiEvent) {
        uiState = PenerbitUiState1(penerbitUiEvent = penerbitUiEvent)
    }

    // Insert the publisher by calling the repository
    fun insertPnrbt() {
        viewModelScope.launch {
            try {
                // Call the insertPenerbit method on the actual PenerbitRepository
                pnrbt.insertPenerbit(uiState.penerbitUiEvent.toPnrbt())
            } catch (e: Exception) {
                // Handle the error (e.g., log or update the UI state with an error message)
                e.printStackTrace()
            }
        }
    }
}


// Define the state class for the UI state (Insert operation)
data class PenerbitUiState1(
    val penerbitUiEvent: PenerbitUiEvent = PenerbitUiEvent(),
    val error: String? = null
)

// Define the event class representing a Penerbit
data class PenerbitUiEvent(
    val idPenerbit: Int = 0,
    val namaPenerbit: String = "",
    val alamatPenerbit: String = "",
    val teleponPenerbit: String = ""
)

// Extension function to convert PenerbitUiEvent to Penerbit (Model class)
fun PenerbitUiEvent.toPnrbt(): Penerbit = Penerbit(
    idPenerbit = idPenerbit,
    namaPenerbit = namaPenerbit,
    alamatPenerbit = alamatPenerbit,
    teleponPenerbit = teleponPenerbit
)

// Extension function to convert Penerbit model to PenerbitUiEvent
fun Penerbit.toUiStatePnrbt(): PenerbitUiEvent = PenerbitUiEvent(
    idPenerbit = idPenerbit,
    namaPenerbit = namaPenerbit,
    alamatPenerbit = alamatPenerbit,
    teleponPenerbit = teleponPenerbit
)
