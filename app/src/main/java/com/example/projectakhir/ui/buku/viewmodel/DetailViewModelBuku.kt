package com.example.projectakhir.ui.buku.viewmodel



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.repository.BukuRepository
import com.example.projectakhir.data.repository.KategoriRepository
import com.example.projectakhir.data.repository.PenerbitRepository
import com.example.projectakhir.data.repository.PenulisRepository
import com.example.projectakhir.ui.navigasi.DestinasiDetailBuku
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch
import retrofit2.Response

//test
class DetailViewModelBuku(
    savedStateHandle: SavedStateHandle,
    private val bukuRepository: BukuRepository,
    private val kategoriRepository: KategoriRepository,
    private val penulisRepository: PenulisRepository,
    private val penerbitRepository: PenerbitRepository
) : ViewModel() {

    private val idBuku: Int = checkNotNull(savedStateHandle[DestinasiDetailBuku.idBukuArg])

    var detailBukuUiState: DetailBukuUiState by mutableStateOf(DetailBukuUiState())
        private set

    init {
        getByIdBuku()
    }

    fun getByIdBuku() {
        viewModelScope.launch {
            detailBukuUiState = DetailBukuUiState(isLoading = true)
            try {
                // Ambil data buku
                val buku = bukuRepository.getBukuById(idBuku)
                // Ambil data kategori, penulis, dan penerbit
                val kategori = kategoriRepository.getKategoriById(buku.idKategori)
                val penulis = penulisRepository.getPenulisById(buku.idPenulis)
                val penerbit = penerbitRepository.getPenerbitById(buku.idPenerbit)

                // Update state
                detailBukuUiState = DetailBukuUiState(
                    detailBukuUiEvent = buku.toDetailBukuUiEvent(kategori, penulis, penerbit),
                    kategori = kategori,
                    penulis = penulis,
                    penerbit = penerbit,
                    isLoading = false
                )
            } catch (e: Exception) {
                detailBukuUiState = DetailBukuUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}


data class DetailBukuUiState(
    val detailBukuUiEvent: DetailBukuUiEvent = DetailBukuUiEvent(),
    val buku: Buku = Buku(idBuku = 0, namaBuku = "", deskripsiBuku = "", tanggalTerbit = "", statusBuku = "", idKategori = 0, idPenulis = 0, idPenerbit = 0),
    val kategori: Kategori = Kategori(idKategori = 0, namaKategori = "", deskripsiKategori = ""),
    val penulis: Penulis = Penulis(idPenulis = 0, namaPenulis = "", biografi = "", kontak = ""),
    val penerbit: Penerbit = Penerbit(idPenerbit = 0, namaPenerbit = "", alamatPenerbit = "", teleponPenerbit = ""),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailBukuUiEvent == DetailBukuUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailBukuUiEvent != DetailBukuUiEvent()
}

data class DetailBukuUiEvent(
    val idBuku: Int = 0,
    val namaBuku: String = "",
    val deskripsiBuku: String = "",
    val tanggalTerbit: String = "",
    val statusBuku: String = "",
    val idKategori: Int = 0,
    val idPenulis: Int = 0,
    val idPenerbit: Int = 0,
    val namaKategori: String = "",
    val namaPenulis: String = "",
    val namaPenerbit: String = ""
)

fun Buku.toDetailBukuUiEvent(
    kategori: Kategori,
    penulis: Penulis,
    penerbit: Penerbit
): DetailBukuUiEvent {
    return DetailBukuUiEvent(
        idBuku = idBuku,
        namaBuku = namaBuku,
        deskripsiBuku = deskripsiBuku,
        tanggalTerbit = tanggalTerbit,
        statusBuku = statusBuku,
        idKategori = idKategori,
        idPenulis = idPenulis,
        idPenerbit = idPenerbit,
        namaKategori = kategori.namaKategori,
        namaPenulis = penulis.namaPenulis,
        namaPenerbit = penerbit.namaPenerbit
    )
}

fun DetailBukuUiEvent.toBuku(): Buku {
    return Buku(
        idBuku = idBuku,
        namaBuku = namaBuku,
        deskripsiBuku = deskripsiBuku,
        tanggalTerbit = tanggalTerbit,
        statusBuku = statusBuku,
        idKategori = idKategori,
        idPenulis = idPenulis,
        idPenerbit = idPenerbit
    )
}



//// Define navigation object for Buku details
//object DestinasiDetailBuku : DestinasiNavigasi {
//    override val route = "detailBuku"
//    const val idBukuArg = "idBuku"
//    override val titleRes = "Detail Buku"
//    const val routeWithArgument = "detailBuku/{idBuku}"
//}
//
//// State untuk UI Detail Buku
//sealed class DetailUiStateBuku {
//    data class Success(
//        val buku: Buku,
//        val kategori: Kategori?,
//        val penulis: Penulis?,
//        val penerbit: Penerbit?
//    ) : DetailUiStateBuku()
//
//    object Loading : DetailUiStateBuku()
//    data class Error(val message: String) : DetailUiStateBuku()
//}
//
//class DetailViewModelBuku(
//    savedStateHandle: SavedStateHandle,
//    private val bukuRepository: BukuRepository
//) : ViewModel() {
//
//    var detailUiState: DetailUiStateBuku by mutableStateOf(DetailUiStateBuku.Loading)
//        private set
//
//    private val idBuku: Int = checkNotNull(savedStateHandle[DestinasiDetailBuku.idBukuArg])
//
//    init {
//        getBukuDetail()
//    }
//
//    private fun getBukuDetail() {
//        viewModelScope.launch {
//            detailUiState = DetailUiStateBuku.Loading
//            try {
//                // Ambil data buku
//                val bukuResponse: Response<Buku> = bukuRepository.getBukuById(idBuku)
//                if (!bukuResponse.isSuccessful || bukuResponse.body() == null) {
//                    detailUiState = DetailUiStateBuku.Error("Gagal memuat data buku")
//                    return@launch
//                }
//                val buku = bukuResponse.body()!!
//
//                // Ambil data kategori
//                val kategoriResponse: Response<List<Kategori>> = bukuRepository.getKategoriList()
//                val kategori = if (kategoriResponse.isSuccessful) {
//                    kategoriResponse.body()?.find { it.idKategori == buku.idKategori }
//                } else {
//                    null
//                }
//
//                // Ambil data penulis
//                val penulisResponse: Response<List<Penulis>> = bukuRepository.getPenulisList()
//                val penulis = if (penulisResponse.isSuccessful) {
//                    penulisResponse.body()?.find { it.idPenulis == buku.idPenulis }
//                } else {
//                    null
//                }
//
//                // Ambil data penerbit
//                val penerbitResponse: Response<List<Penerbit>> = bukuRepository.getPenerbitList()
//                val penerbit = if (penerbitResponse.isSuccessful) {
//                    penerbitResponse.body()?.find { it.idPenerbit == buku.idPenerbit }
//                } else {
//                    null
//                }
//
//                // Update state dengan data yang berhasil diambil
//                detailUiState = DetailUiStateBuku.Success(buku, kategori, penulis, penerbit)
//            } catch (e: Exception) {
//                detailUiState = DetailUiStateBuku.Error(e.message ?: "Terjadi kesalahan")
//            }
//        }
//    }
//}








//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.projectakhir.data.model.Buku
//import com.example.projectakhir.data.model.Kategori
//import com.example.projectakhir.data.model.Penerbit
//import com.example.projectakhir.data.model.Penulis
//import com.example.projectakhir.data.repository.BukuRepository
//import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
//import kotlinx.coroutines.launch
//
//// Define navigation object for Buku details
//object DestinasiDetailBuku : DestinasiNavigasi {
//    override val route = "detailBuku"
//    const val idBukuArg = "idBuku"
//    override val titleRes = "Detail Buku"
//    const val routeWithArgument = "detailBuku/{idBuku}"
//}
//
//
//
//// State untuk UI Detail Buku
//sealed class DetailUiStateBuku {
//    data class Success(
//        val buku: Buku,
//        val kategori: Kategori?,
//        val penulis: Penulis?,
//        val penerbit: Penerbit?
//    ) : DetailUiStateBuku()
//
//    object Loading : DetailUiStateBuku()
//    data class Error(val message: String) : DetailUiStateBuku()
//}
//
//class DetailViewModelBuku(
//    savedStateHandle: SavedStateHandle,
//    private val bukuRepository: BukuRepository
//) : ViewModel() {
//
//    var detailUiState: DetailUiStateBuku by mutableStateOf(DetailUiStateBuku.Loading)
//        private set
//
//    private val idBuku: Int = checkNotNull(savedStateHandle[DestinasiDetailBuku.idBukuArg])
//
//    init {
//        getBukuDetail()
//    }
//
//    private fun getBukuDetail() {
//        viewModelScope.launch {
//            detailUiState = DetailUiStateBuku.Loading
//            try {
//                // Ambil data buku
//                val bukuResponse = bukuRepository.getBukuById(idBuku)
//                if (!bukuResponse.isSuccessful || bukuResponse.body() == null) {
//                    detailUiState = DetailUiStateBuku.Error("Gagal memuat data buku")
//                    return@launch
//                }
//                val buku = bukuResponse.body()!!
//
//                // Ambil data kategori
//                val kategoriResponse = bukuRepository.getKategoriList()
//                val kategori = if (kategoriResponse.isSuccessful) {
//                    kategoriResponse.body()?.find { it.idKategori == buku.idKategori }
//                } else {
//                    null
//                }
//
//                // Ambil data penulis
//                val penulisResponse = bukuRepository.getPenulisList()
//                val penulis = if (penulisResponse.isSuccessful) {
//                    penulisResponse.body()?.find { it.idPenulis == buku.idPenulis }
//                } else {
//                    null
//                }
//
//                // Ambil data penerbit
//                val penerbitResponse = bukuRepository.getPenerbitList()
//                val penerbit = if (penerbitResponse.isSuccessful) {
//                    penerbitResponse.body()?.find { it.idPenerbit == buku.idPenerbit }
//                } else {
//                    null
//                }
//
//                // Update state dengan data yang berhasil diambil
//                detailUiState = DetailUiStateBuku.Success(buku, kategori, penulis, penerbit)
//            } catch (e: Exception) {
//                detailUiState = DetailUiStateBuku.Error(e.message ?: "Terjadi kesalahan")
//            }
//        }
//    }
//}