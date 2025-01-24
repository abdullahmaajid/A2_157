package com.example.projectakhir.ui.penulis.viewmodel





import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.repository.PenulisRepository
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object DestinasiDetailPenulis : DestinasiNavigasi {
    override val route = "detailPenulis"
    const val idPenulisiArg = "idPenulis"
    override val titleRes = "Detail Penulis"
    const val routeWithArgument = "detailPenulis/{idPenulis}"
}


class DetailViewModelPenulis(
    savedStateHandle: SavedStateHandle,
    private val penulisRepository: PenulisRepository
) : ViewModel() {

    private val idPenulis: Int = checkNotNull(savedStateHandle[DestinasiDetailPenulis.idPenulisiArg])

    var detailUiStateView: DetailUiStatePenulis by mutableStateOf(DetailUiStatePenulis())
        private set

    init {
        getPenulisById()
    }

    private fun getPenulisById() {
        viewModelScope.launch {
            detailUiStateView = DetailUiStatePenulis(isLoading = true)
            try {
                val result = penulisRepository.getPenulisById(idPenulis)
                detailUiStateView = DetailUiStatePenulis(
                    detailUiEventView = result.toPenulisDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiStateView = DetailUiStatePenulis(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}


data class DetailUiStatePenulis(
    val detailUiEventView: PenulisDetailUiEvent = PenulisDetailUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEventView == PenulisDetailUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEventView != PenulisDetailUiEvent()
}

//@Serializable
//data class Penulis(
//    @SerialName("id_penulis")
//    val idPenulis: Int,
//
//    @SerialName("nama_penulis")
//    val namaPenulis: String,
//
//    @SerialName("biografi")
//    val biografi: String,
//
//    @SerialName("kontak")
//    val kontak: String
//)
data class PenulisDetailUiEvent(
    val idPenulis: Int = 0,
    val namaPenulis: String = "",
    val biografi: String = "",
    val kontak: String = "",
)


fun Penulis.toPenulisDetailUiEvent(): PenulisDetailUiEvent {
    return PenulisDetailUiEvent(
        idPenulis = idPenulis,
        namaPenulis = namaPenulis,
        biografi = biografi,
        kontak = kontak
    )
}

fun PenulisDetailUiEvent.toPenulis(): Penulis {
    return Penulis(
        idPenulis = idPenulis,
        namaPenulis = namaPenulis,
        biografi = biografi,
        kontak = kontak
    )
}
