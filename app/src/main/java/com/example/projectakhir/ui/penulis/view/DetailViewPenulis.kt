package com.example.projectakhir.ui.penulis.view


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
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
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.penulis.viewmodel.DetailUiStatePenulis
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import com.example.projectakhir.ui.penulis.viewmodel.DetailViewModelPenulis
import com.example.projectakhir.ui.penulis.viewmodel.toPenulis

object DestinasiDetailPenulis : DestinasiNavigasi {
    override val route = "detailPenulis"
    const val idPenulisArg = "idPenulis"
    override val titleRes = "Detail Penulis"
    const val routeWithArgument = "detailPenulis/{idPenulis}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailViewPenulis(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelPenulis = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPenulis.titleRes,
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
                    contentDescription = "Edit Penulis"
                )
            }
        }
    ) { innerPadding ->
        BodyDetailPenulis(
            detailUiStatePenulis = viewModel.detailUiStateView,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailPenulis(
    detailUiStatePenulis: DetailUiStatePenulis,
    modifier: Modifier = Modifier
) {
    when {
        detailUiStatePenulis.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailUiStatePenulis.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailUiStatePenulis.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailUiStatePenulis.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailPenulis(
                    penulis = detailUiStatePenulis.detailUiEventView.toPenulis(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailPenulis(
    modifier: Modifier = Modifier,
    penulis: Penulis
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
            ComponentDetailPenulis(judul = "ID Penulis", isinya = penulis.idPenulis.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenulis(judul = "Nama Penulis", isinya = penulis.namaPenulis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenulis(judul = "Biografi", isinya = penulis.biografi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenulis(judul = "Kontak", isinya = penulis.kontak)
        }
    }
}

@Composable
fun ComponentDetailPenulis(
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
