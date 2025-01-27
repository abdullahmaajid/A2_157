package com.example.projectakhir.ui.buku.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.repository.BukuRepository
import com.example.projectakhir.data.repository.KategoriRepository
import com.example.projectakhir.data.repository.PenerbitRepository
import com.example.projectakhir.data.repository.PenulisRepository
import com.example.projectakhir.ui.navigasi.DestinasiUpdateBuku
import kotlinx.coroutines.launch

class UpdateViewModelBuku(
    savedStateHandle: SavedStateHandle,
    private val bukuRepository: BukuRepository,
    private val kategoriRepository: KategoriRepository,
    private val penulisRepository: PenulisRepository,
    private val penerbitRepository: PenerbitRepository
) : ViewModel() {

    // State untuk UI
    var uiState by mutableStateOf(BukuUiState())
        private set

    // State untuk dropdown
    var kategoriList by mutableStateOf<List<Kategori>>(emptyList())
    var penulisList by mutableStateOf<List<Penulis>>(emptyList())
    var penerbitList by mutableStateOf<List<Penerbit>>(emptyList())

    // Ambil idProperti dari SavedStateHandle
    private val idBuku: Int = checkNotNull(savedStateHandle[DestinasiUpdateBuku.idBukuArg])

    // Fungsi untuk mengambil data dropdown dan data properti
    fun fetchData() {
        viewModelScope.launch {
            try {
                // Ambil data dropdown
                kategoriList = kategoriRepository.getKategori()
                penulisList = penulisRepository.getPenulis()
                penerbitList = penerbitRepository.getPenerbit()

                // Ambil data buku berdasarkan ID
                val buku = bukuRepository.getBukuById(idBuku)
                uiState = uiState.copy(
                    bukuUiEvent = BukuUiEvent(
                        idBuku = buku.idBuku,
                        namaBuku = buku.namaBuku,
                        deskripsiBuku = buku.deskripsiBuku,
                        tanggalTerbit = buku.tanggalTerbit,
                        statusBuku = StatusBuku.valueOf(buku.statusBuku),
                        idKategori = buku.idKategori,
                        idPenulis = buku.idPenulis,
                        idPenerbit = buku.idPenerbit
                    )
                )
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessage = "Gagal mengambil data: ${e.message}")
            }
        }
    }

    // Fungsi untuk update state dari form input
    fun updateBukuState(event: BukuUiEvent) {
        uiState = uiState.copy(bukuUiEvent = event)
    }

    // Fungsi untuk update properti
    fun updateBuku() {
        viewModelScope.launch {
            try {
                bukuRepository.updateBuku(idBuku, uiState.bukuUiEvent.toBuku())
                uiState = uiState.copy(errorMessage = null) // Reset error state
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessage = "Gagal mengupdate buku: ${e.message}")
            }
        }
    }
}