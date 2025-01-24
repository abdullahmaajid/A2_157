package com.example.projectakhir.ui.penerbit.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.repository.PenerbitRepository
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

// Define navigation object for Penerbit details
object DestinasiDetailPenerbit : DestinasiNavigasi {
    override val route = "detailPenerbit"
    override val titleRes = "Detail Penerbit"
    const val routeWithArgument = "detailPenerbit/{idPenerbit}"
    const val idPenerbitArg = "idPenerbit"
}

// ViewModel for Penerbit details
class DetailViewModelPenerbit(
    savedStateHandle: SavedStateHandle,
    private val penerbitRepository: PenerbitRepository
) : ViewModel() {
    // Retrieve the idPenerbit argument from the navigation route
    private val idPenerbit: Int = checkNotNull(savedStateHandle[DestinasiDetailPenerbit.idPenerbitArg])

    var detailUiStateView: DetailUiStatePenerbit by mutableStateOf(DetailUiStatePenerbit())
        private set

    init {
        getPenerbitById()
    }

    private fun getPenerbitById() {
        viewModelScope.launch {
            detailUiStateView = DetailUiStatePenerbit(isLoading = true)
            try {
                val result = penerbitRepository.getPenerbitById(idPenerbit)
                detailUiStateView = DetailUiStatePenerbit(
                    detailUiEventView = result.toPenerbitDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiStateView = DetailUiStatePenerbit(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

// UI state for Penerbit detail screen
data class DetailUiStatePenerbit(
    val detailUiEventView: PenerbitDetailUiEvent = PenerbitDetailUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEventView == PenerbitDetailUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEventView != PenerbitDetailUiEvent()
}

// UI event for Penerbit details
data class PenerbitDetailUiEvent(
    val idPenerbit: Int = 0,
    val namaPenerbit: String = "",
    val alamatPenerbit: String = "",
    val teleponPenerbit: String = ""
)

// Extension function to map Penerbit to PenerbitDetailUiEvent
fun Penerbit.toPenerbitDetailUiEvent(): PenerbitDetailUiEvent {
    return PenerbitDetailUiEvent(
        idPenerbit = idPenerbit,
        namaPenerbit = namaPenerbit,
        alamatPenerbit = alamatPenerbit,
        teleponPenerbit = teleponPenerbit
    )
}

// Extension function to map PenerbitDetailUiEvent back to Penerbit
fun PenerbitDetailUiEvent.toPenerbit(): Penerbit {
    return Penerbit(
        idPenerbit = idPenerbit,
        namaPenerbit = namaPenerbit,
        alamatPenerbit = alamatPenerbit,
        teleponPenerbit = teleponPenerbit
    )
}
