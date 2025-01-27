package com.example.projectakhir.ui.buku.viewmodel







import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.repository.BukuRepository
import com.example.projectakhir.data.repository.KategoriRepository
import com.example.projectakhir.data.repository.PenerbitRepository
import com.example.projectakhir.data.repository.PenulisRepository
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch


// ViewModel for inserting a book (Buku)
class InsertViewModelBuku(
    bukuRepository1: SavedStateHandle,
    private val bukuRepository: BukuRepository,
    private val kategoriRepository: KategoriRepository,
    private val penulisRepository: PenulisRepository,
    private val penerbitRepository: PenerbitRepository,


    ) : ViewModel() {

    // Define the UI state variable for Insert operation
    var uiState by mutableStateOf(BukuUiState())
        private set

    // State untuk dropdown
    var kategoriList by mutableStateOf<List<Kategori>>(emptyList())
    var penulisList by mutableStateOf<List<Penulis>>(emptyList())
    var penerbitList by mutableStateOf<List<Penerbit>>(emptyList())

    // Fungsi untuk mengambil data dropdown
    fun fetchDropdownData() {
        viewModelScope.launch {
            try {
                kategoriList = kategoriRepository.getKategori()
                penulisList = penulisRepository.getPenulis()
                penerbitList = penerbitRepository.getPenerbit()
            } catch (e: Exception) {
                // Handle error
                uiState = uiState.copy(errorMessage = "Gagal mengambil data dropdown: ${e.message}")
            }
        }
    }

    // Fungsi untuk update state dari form input
    fun updateBukuState(event: BukuUiEvent) {
        uiState = uiState.copy(bukuUiEvent = event)
    }


    // Insert the book by calling the repository
    fun insertBuku() {
        viewModelScope.launch {
            try {
                // Call the insertBuku method on the actual BukuRepository
                bukuRepository.insertBuku(uiState.bukuUiEvent.toBuku())
                uiState = uiState.copy(errorMessage = null) // Reset error state
            } catch (e: Exception) {
                // Handle the error (e.g., log or update the UI state with an error message)
                uiState =
                    uiState.copy(errorMessage = "Failed to insert buku: ${e.message}")

            }
        }
    }

}


// State untuk UI
data class BukuUiState(
    val bukuUiEvent: BukuUiEvent = BukuUiEvent(),
    val errorMessage: String? = null
)

enum class StatusBuku {
    Tersedia,
    Habis,
    Dipesan
}

// Define the event class representing a book
data class BukuUiEvent(
    val idBuku: Int = 0,
    val namaBuku: String = "",
    val deskripsiBuku: String = "",
    val tanggalTerbit: String = "",
    val statusBuku: StatusBuku = StatusBuku.Tersedia,
    val idKategori: Int = 0,
    val idPenulis: Int = 0,
    val idPenerbit: Int = 0
)

// Extension function to convert BukuUiEvent to Buku (Model class)
fun BukuUiEvent.toBuku(): Buku = Buku(
    idBuku = idBuku,
    namaBuku = namaBuku,
    deskripsiBuku = deskripsiBuku,
    tanggalTerbit = tanggalTerbit,
    statusBuku = statusBuku.name,
    idKategori = idKategori,
    idPenulis = idPenulis,
    idPenerbit = idPenerbit
)








