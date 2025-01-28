package com.example.projectakhir.ui.kategori.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import com.example.projectakhir.R



object DestinasiDetailKategori : DestinasiNavigasi {
    override val route = "detailKategori"
    const val idKategoriArg = "idKategori"
    override val titleRes = "Detail Kategori"
    const val routeWithArgument = "detailKategori/{idKategori}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailViewKategori(
    navigateBackToHomeKategori: () -> Unit,
    navigateBackToDetailBuku: () -> Unit,
    navigateToEdit: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
            //.background(colorResource(id = R.color.accent2)), // Set background color to primary1
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailKategori.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBackToHomeKategori // Default back to home
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEdit(viewModel.detailUiStateView.detailUiEventView.idKategori) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp),
                containerColor = colorResource(id = R.color.black) // Set FAB color to accent2
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kategori",
                    tint = Color.White // Set icon color to white
                )
            }
        }
    ) { innerPadding ->
        BodyDetailKategori(
            detailUiStateKategori = viewModel.detailUiStateView,
            modifier = Modifier.padding(innerPadding),
            navigateBackToDetailBuku = navigateBackToDetailBuku,
            navigateBackToHomeKategori = navigateBackToHomeKategori
        )
    }
}

@Composable
fun BodyDetailKategori(
    detailUiStateKategori: DetailUiStateKategori,
    modifier: Modifier = Modifier,
    navigateBackToDetailBuku: () -> Unit,
    navigateBackToHomeKategori: () -> Unit
) {
    when {
        detailUiStateKategori.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White) // Set progress color to white
            }
        }
        detailUiStateKategori.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailUiStateKategori.errorMessage,
                    color = Color.Red,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
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
                    modifier = modifier,
                    navigateBackToDetailBuku = navigateBackToDetailBuku,
                    navigateBackToHomeKategori = navigateBackToHomeKategori
                )
            }
        }
    }
}

@Composable
fun ItemDetailKategori(
    modifier: Modifier = Modifier,
    kategori: Kategori,
    navigateBackToDetailBuku: () -> Unit,
    navigateBackToHomeKategori: () -> Unit
) {
    Card(
        modifier = modifier
            //.background(color = colorResource(id = R.color.secondary))
            .fillMaxWidth()
            .padding(top = 20.dp),
        shape = RoundedCornerShape(16.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Add shadow
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.black), // Set card color to accent2
            //contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Add an icon from drawable
            Image(
                painter = painterResource(id = R.drawable.kategoriputih), // Icon from drawable
                contentDescription = "Kategori Icon",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            ComponentDetailKategori(judul = "ID Kategori", isinya = kategori.idKategori.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailKategori(judul = "Nama Kategori", isinya = kategori.namaKategori)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailKategori(judul = "Deskripsi", isinya = kategori.deskripsiKategori)

            // Tombol untuk kembali ke Detail Buku
            Button(
                onClick = navigateBackToDetailBuku,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.white), // Set button color to accent2
                    contentColor = Color.Black // Set text color to white
                )
            ) {
                Text(text = "Kembali ke Detail Buku")
            }

            // Tombol untuk kembali ke Home Kategori
            Button(
                onClick = navigateBackToHomeKategori,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.white), // Set button color to accent2
                    contentColor = Color.Black // Set text color to white
                )
            ) {
                Text(text = "Kembali ke Home Kategori")
            }
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
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
