package com.example.projectakhir.ui.penulis.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
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
import com.example.projectakhir.R
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
    navigateBackToHomePenulis: () -> Unit,
    navigateBackToDetailBuku: () -> Unit,
    navigateToEdit: (Int) -> Unit,
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
                navigateUp = navigateBackToHomePenulis // Default back to home
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEdit(viewModel.detailUiStateView.detailUiEventView.idPenulis) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp),
                containerColor = colorResource(id = R.color.black) // Set FAB color to accent2
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Penulis",
                    tint = Color.White // Set icon color to white
                )
            }
        }
    ) { innerPadding ->
        BodyDetailPenulis(
            detailUiStatePenulis = viewModel.detailUiStateView,
            modifier = Modifier.padding(innerPadding),
            navigateBackToDetailBuku = navigateBackToDetailBuku,
            navigateBackToHomePenulis = navigateBackToHomePenulis
        )
    }
}

@Composable
fun BodyDetailPenulis(
    detailUiStatePenulis: DetailUiStatePenulis,
    modifier: Modifier = Modifier,
    navigateBackToDetailBuku: () -> Unit,
    navigateBackToHomePenulis: () -> Unit
) {
    when {
        detailUiStatePenulis.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White) // Set progress color to white
            }
        }
        detailUiStatePenulis.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailUiStatePenulis.errorMessage,
                    color = Color.Red,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
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
                    modifier = modifier,
                    navigateBackToDetailBuku = navigateBackToDetailBuku,
                    navigateBackToHomePenulis = navigateBackToHomePenulis
                )
            }
        }
    }
}

@Composable
fun ItemDetailPenulis(
    modifier: Modifier = Modifier,
    penulis: Penulis,
    navigateBackToDetailBuku: () -> Unit,
    navigateBackToHomePenulis: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        shape = RoundedCornerShape(16.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Add shadow
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.black), // Set card color to accent2
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Add an icon from drawable
            Image(
                painter = painterResource(id = R.drawable.penulisputih), // Icon from drawable
                contentDescription = "Penulis Icon",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            ComponentDetailPenulis(judul = "ID Penulis", isinya = penulis.idPenulis.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenulis(judul = "Nama Penulis", isinya = penulis.namaPenulis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenulis(judul = "Biografi", isinya = penulis.biografi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenulis(judul = "Kontak", isinya = penulis.kontak)

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

            // Tombol untuk kembali ke Home Penulis
            Button(
                onClick = navigateBackToHomePenulis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.white), // Set button color to accent2
                    contentColor = Color.Black // Set text color to white
                )
            ) {
                Text(text = "Kembali ke Home Penulis")
            }
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
