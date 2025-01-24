package com.example.projectakhir.ui.kategori.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.kategori.viewmodel.DetailUiStateKategori
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import com.example.projectakhir.ui.kategori.viewmodel.DetailViewModelKategori
import com.example.projectakhir.ui.kategori.viewmodel.toKategori



object DestinasiDetailKategori : DestinasiNavigasi {
    override val route = "detailKategori"
    const val idKategoriArg = "idKategori"
    override val titleRes = "Detail Kategori"
    const val routeWithArgument = "detailKategori/{idKategori}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailViewKategori(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailKategori.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEdit,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kategori"
                )
            }
        }
    ) { innerPadding ->
        BodyDetailKategori(
            detailUiStateKategori = viewModel.detailUiStateView,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailKategori(
    detailUiStateKategori: DetailUiStateKategori,
    modifier: Modifier = Modifier
) {
    when {
        detailUiStateKategori.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailUiStateKategori.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailUiStateKategori.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailUiStateKategori.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailKategori(
                    kategori = detailUiStateKategori.detailUiEventView.toKategori(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailKategori(
    modifier: Modifier = Modifier,
    kategori: Kategori
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ComponentDetailKategori(judul = "ID Kategori", isinya = kategori.idKategori.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailKategori(judul = "Nama Kategori", isinya = kategori.namaKategori)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailKategori(judul = "Deskripsi", isinya = kategori.deskripsiKategori)
        }
    }
}

@Composable
fun ComponentDetailKategori(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
