package com.example.projectakhir.ui.penerbit.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.projectakhir.R
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.penerbit.viewmodel.DetailUiStatePenerbit
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import com.example.projectakhir.ui.penerbit.viewmodel.DetailViewModelPenerbit
import com.example.projectakhir.ui.penerbit.viewmodel.toPenerbit

object DestinasiDetailPenerbit : DestinasiNavigasi {
    override val route = "detailPenerbit"
    override val titleRes = "Detail Penerbit"
    const val routeWithArgument = "detailPenerbit/{idPenerbit}"
    const val idPenerbitArg = "idPenerbit"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailViewPenerbit(
    navigateBackToHomePenerbit: () -> Unit,
    navigateBackToDetailBuku: () -> Unit,
    navigateToEdit: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelPenerbit = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPenerbit.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBackToHomePenerbit
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEdit(viewModel.detailUiStateView.detailUiEventView.idPenerbit) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp),
                containerColor = colorResource(id = R.color.black)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Penerbit",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        BodyDetailPenerbit(
            detailUiStatePenerbit = viewModel.detailUiStateView,
            modifier = Modifier.padding(innerPadding),
            navigateBackToDetailBuku = navigateBackToDetailBuku,
            navigateBackToHomePenerbit = navigateBackToHomePenerbit
        )
    }
}

@Composable
fun BodyDetailPenerbit(
    detailUiStatePenerbit: DetailUiStatePenerbit,
    modifier: Modifier = Modifier,
    navigateBackToDetailBuku: () -> Unit,
    navigateBackToHomePenerbit: () -> Unit
) {
    when {
        detailUiStatePenerbit.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        detailUiStatePenerbit.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailUiStatePenerbit.errorMessage,
                    color = Color.Red,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        detailUiStatePenerbit.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailPenerbit(
                    penerbit = detailUiStatePenerbit.detailUiEventView.toPenerbit(),
                    modifier = modifier,
                    navigateBackToDetailBuku = navigateBackToDetailBuku,
                    navigateBackToHomePenerbit = navigateBackToHomePenerbit
                )
            }
        }
    }
}

@Composable
fun ItemDetailPenerbit(
    modifier: Modifier = Modifier,
    penerbit: Penerbit,
    navigateBackToDetailBuku: () -> Unit,
    navigateBackToHomePenerbit: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.black)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.penerbitputih), // Icon from drawable
                contentDescription = "Penerbit Icon",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            ComponentDetailPenerbit(judul = "ID Penerbit", isinya = penerbit.idPenerbit.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenerbit(judul = "Nama Penerbit", isinya = penerbit.namaPenerbit)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenerbit(judul = "Alamat", isinya = penerbit.alamatPenerbit)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPenerbit(judul = "Telepon", isinya = penerbit.teleponPenerbit)

            Button(
                onClick = navigateBackToDetailBuku,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.white),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Kembali ke Detail Buku")
            }

            Button(
                onClick = navigateBackToHomePenerbit,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.white),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Kembali ke Home Penerbit")
            }
        }
    }
}

@Composable
fun ComponentDetailPenerbit(
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
