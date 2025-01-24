package com.example.projectakhir.ui.kategori.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.repository.KategoriRepository
import kotlinx.coroutines.launch

// ViewModel for inserting a category (Kategori)
class InsertViewModelKategori(
    //private val kategoriRepository: KategoriRepository
    kategoriRepository: SavedStateHandle,
    private val ktgr: KategoriRepository
) : ViewModel() {
    // Define the UI state variable for Insert operation
    var uiState by mutableStateOf(KategoriUiState1())
        private set

    // Update the UI state with the new category event
    fun updateInsertKtgrState(kategoriUiEvent: KategoriUiEvent) {
        uiState = KategoriUiState1(kategoriUiEvent = kategoriUiEvent)
    }

    // Insert the location by calling the r epository
    fun insertKtgr() {
        viewModelScope.launch {
            try {
                // Call the insertLokasi method on the actual LokasiRepository
                ktgr.insertKategori(uiState.kategoriUiEvent.toKtgr())
            } catch (e: Exception) {
                // Handle the error (e.g., log or update the UI state with an error message)
                e.printStackTrace()
            }
        }
    }
}

// Define the state class for the UI state (Insert operation)
data class KategoriUiState1(
    val kategoriUiEvent: KategoriUiEvent = KategoriUiEvent(),
    val error: String? = null
)

// Define the event class representing a category
data class KategoriUiEvent(
    val idKategori: Int = 0,
    val namaKategori: String = "",
    val deskripsiKategori: String = ""
)

// Extension function to convert KategoriUiEvent to Kategori (Model class)
fun KategoriUiEvent.toKtgr(): Kategori = Kategori(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskripsiKategori = deskripsiKategori
)

// Extension function to convert Kategori model to KategoriUiEvent
fun Kategori.toUiStateKtgr(): KategoriUiEvent = KategoriUiEvent(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskripsiKategori = deskripsiKategori
)