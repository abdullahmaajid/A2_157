package com.example.projectakhir.ui.kategori.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projectakhir.R
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.kategori.viewmodel.HomeViewModelKategori
import com.example.projectakhir.ui.kategori.viewmodel.KategoriUiState
import androidx.compose.ui.res.colorResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewKategori(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    navController: NavController,
    onBackClick: () -> Unit = {},
    viewModel: HomeViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            KategoriTopBar(navController = navController, viewModel = viewModel, scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            KategoriFab(onClick = navigateToItemEntry)
        },
    ) { innerPadding ->
        KategoriStatus(
            kategoriUiState = viewModel.kategoriUiState,
            retryAction = { viewModel.getKategori() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteKategori(it.idKategori)
                viewModel.getKategori()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KategoriTopBar(
    navController: NavController,
    viewModel: HomeViewModelKategori,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = Modifier, // Hapus Modifier.background di sini
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.white) // Atur warna latar belakang di sini
        ),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Gunakan fillMaxWidth agar title memenuhi lebar TopAppBar
                    .clip(MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Daftar Kategori",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black // Atur warna ikon back agar sesuai dengan tema
                )
            }
        },
        actions = {
            IconButton(onClick = { viewModel.getKategori() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    tint = Color.Black // Atur warna ikon refresh agar sesuai dengan tema
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun KategoriFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp),
        containerColor = colorResource(id = R.color.white) // Warna latar belakang FAB
    ) {
        Icon(
            imageVector = Icons.Default.Add, // Ikon tambah
            contentDescription = "Tambah Kategori",
            tint = Color.Black // Warna ikon putih
        )
    }
}

@Composable
fun KategoriStatus(
    kategoriUiState: KategoriUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kategori) -> Unit = {},
    onDetailClick: (Int) -> Unit
) {
    when (kategoriUiState) {
        is KategoriUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is KategoriUiState.Success -> {
            if (kategoriUiState.kategori.isEmpty()) {
                EmptyKategoriView(modifier = modifier)
            } else {
                KategoriLayout(
                    kategori = kategoriUiState.kategori,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.idKategori) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }
        is KategoriUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun EmptyKategoriView(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Tidak ada data kategori", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.no_wifi),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.no_wifi), contentDescription = "")
            Text(
                text = stringResource(R.string.loading_failed),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = retryAction, modifier = Modifier.padding(top = 16.dp)) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun KategoriLayout(
    kategori: List<Kategori>,
    modifier: Modifier = Modifier,
    onDetailClick: (Kategori) -> Unit,
    onDeleteClick: (Kategori) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.background(color = colorResource(id = R.color.white)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(kategori) { kategori ->
            KategoriCard(
                kategori = kategori,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(kategori) },
                onDeleteClick = { onDeleteClick(kategori) }
            )
        }
    }
}

@Composable
fun KategoriCard(
    kategori: Kategori,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    onDeleteClick: (Kategori) -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.black))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ganti ikon buku dengan ikon kategori
                Icon(
                    painter = painterResource(id = R.drawable.kategoriputih), // Ganti dengan drawable kategori yang sesuai
                    contentDescription = "Kategori",
                    tint = Color.White
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = kategori.namaKategori,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(kategori) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Kategori",
                        tint = Color.White
                    )
                }
            }
            Divider()
            Column {
                Text(
                    text = "ID Kategori: ${kategori.idKategori}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Deskripsi: ${kategori.deskripsiKategori}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
