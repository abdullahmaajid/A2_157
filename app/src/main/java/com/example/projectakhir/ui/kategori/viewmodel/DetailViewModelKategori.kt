package com.example.projectakhir.ui.kategori.viewmodel



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.repository.KategoriRepository
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

//// Define navigation object for Kategori details
//object DestinasiDetailKategori : DestinasiNavigasi {
//    override val route = "detail_kategori"
//    const val ID_KATEGORI = "id_kategori"
//    override val titleRes = "Detail Kategori"
//    val routeWithArg = "$route/{$ID_KATEGORI}"
//}

object DestinasiDetailKategori : DestinasiNavigasi {
    override val route = "detailKategori"
    const val idKategoriArg = "idKategori"
    override val titleRes = "Detail Kategori"
    const val routeWithArgument = "detailKategori/{idKategori}"
}

// ViewModel for Kategori details
class DetailViewModelKategori(
    savedStateHandle: SavedStateHandle,
    private val kategoriRepository: KategoriRepository
) : ViewModel() {
    // Retrieve the ID_KATEGORI argument from the navigation route
    private val idKategori: Int = checkNotNull(savedStateHandle[DestinasiDetailKategori.idKategoriArg])

    var detailUiStateView: DetailUiStateKategori by mutableStateOf(DetailUiStateKategori())
        private set

    init {
        getKategoriById()
    }

    private fun getKategoriById() {
        viewModelScope.launch {
            detailUiStateView = DetailUiStateKategori(isLoading = true)
            try {
                val result = kategoriRepository.getKategoriById(idKategori)
                detailUiStateView = DetailUiStateKategori(
                    detailUiEventView = result.toKategoriDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiStateView = DetailUiStateKategori(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

// UI state for Kategori detail screen
data class DetailUiStateKategori(
    val detailUiEventView: KategoriDetailUiEvent = KategoriDetailUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEventView == KategoriDetailUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEventView != KategoriDetailUiEvent()
}

// UI event for Kategori details
data class KategoriDetailUiEvent(
    val idKategori: Int = 0,
    val namaKategori: String = "",
    val deskripsiKategori: String = ""
)

// Extension function to map Kategori to KategoriDetailUiEvent
fun Kategori.toKategoriDetailUiEvent(): KategoriDetailUiEvent {
    return KategoriDetailUiEvent(
        idKategori = idKategori,
        namaKategori = namaKategori,
        deskripsiKategori = deskripsiKategori
    )
}

// Extension function to map KategoriDetailUiEvent back to Kategori
fun KategoriDetailUiEvent.toKategori(): Kategori {
    return Kategori(
        idKategori = idKategori,
        namaKategori = namaKategori,
        deskripsiKategori = deskripsiKategori
    )
}
