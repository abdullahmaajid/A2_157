package com.example.projectakhir.ui.buku.view





import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.buku.viewmodel.DetailBukuUiState
import com.example.projectakhir.ui.buku.viewmodel.DetailViewModelBuku
import com.example.projectakhir.ui.buku.viewmodel.toBuku
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiDetailKategori
import com.example.projectakhir.ui.navigasi.DestinasiDetailPenerbit
import com.example.projectakhir.ui.navigasi.DestinasiDetailPenulis
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi

object DestinasiDetailBuku : DestinasiNavigasi {
    override val route = "detailBuku"
    override val titleRes = "Detail Buku"
    const val idBukuArg = "idBuku"
    const val routeWithArgument = "detailBuku/{idBuku}"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailViewBuku(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController, // Receive NavController
    viewModel: DetailViewModelBuku = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailBuku.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEdit,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Buku")
            }
        }
    ) { innerPadding ->
        BodyDetailBuku(
            detailBukuUiState = viewModel.detailBukuUiState,
            modifier = Modifier.padding(innerPadding),
            navController = navController // Pass NavController
        )
    }
}



@Composable
fun BodyDetailBuku(
    detailBukuUiState: DetailBukuUiState,
    modifier: Modifier = Modifier,
    navController: NavController // Receive NavController here
) {
    when {
        detailBukuUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailBukuUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailBukuUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailBukuUiState.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                val kategori = detailBukuUiState.kategori
                val penulis = detailBukuUiState.penulis
                val penerbit = detailBukuUiState.penerbit

                ItemDetailBuku(
                    buku = detailBukuUiState.detailBukuUiEvent.toBuku(),
                    kategori = kategori,
                    penulis = penulis,
                    penerbit = penerbit,
                    navController = navController // Pass NavController to ItemDetailBuku
                )
            }
        }
    }
}





@Composable
fun ItemDetailBuku(
    modifier: Modifier = Modifier,
    buku: Buku,
    kategori: Kategori,
    penulis: Penulis,
    penerbit: Penerbit,
    navController: NavController // Receive NavController here
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ComponentDetailBuku(judul = "ID Buku", isinya = buku.idBuku.toString())
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBuku(judul = "Nama Buku", isinya = buku.namaBuku)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBuku(judul = "Deskripsi", isinya = buku.deskripsiBuku)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBuku(judul = "Tanggal Terbit", isinya = buku.tanggalTerbit)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBuku(judul = "Status", isinya = buku.statusBuku)
            Spacer(modifier = Modifier.padding(4.dp))

            // Navigating to Category, Author, Publisher using NavController
            ComponentDetailBuku(
                judul = "Kategori",
                isinya = kategori.namaKategori,
                onClick = { navController.navigate("${DestinasiDetailKategori.route}/${kategori.idKategori}") }
            )
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBuku(
                judul = "Penulis",
                isinya = penulis.namaPenulis,
                onClick = { navController.navigate("${DestinasiDetailPenulis.route}/${penulis.idPenulis}") }
            )
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBuku(
                judul = "Penerbit",
                isinya = penerbit.namaPenerbit,
                onClick = { navController.navigate("${DestinasiDetailPenerbit.route}/${penerbit.idPenerbit}") }
            )
        }
    }
}



@Composable
fun ComponentDetailBuku(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
    onClick: (() -> Unit)? = null // Optional onClick parameter
) {
    println("ComponentDetailBuku: Displaying $judul - $isinya")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }, // Handle clickability
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = if (onClick != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
        )
    }
}

