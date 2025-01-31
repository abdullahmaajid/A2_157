package com.example.projectakhir.ui.buku.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.buku.viewmodel.HomeViewModelBuku
import com.example.projectakhir.R
import com.example.projectakhir.data.model.Buku
import com.example.projectakhir.ui.buku.viewmodel.BukuUiState
import com.example.projectakhir.ui.buku.viewmodel.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewBuku(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    navController: NavController,
    onBackClick: () -> Unit = {},
    viewModel: HomeViewModelBuku = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BukuTopBar(navController = navController, viewModel = viewModel, scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
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
        },
    ) { innerPadding ->
        BukuStatus(
            homeUiState = viewModel.homeUiState,
            retryAction = { viewModel.getBuku() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteBuku(it.idBuku)
                viewModel.getBuku()
            },
            onBackClick = onBackClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BukuTopBar(
    navController: NavController,
    viewModel: HomeViewModelBuku,
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
                    text = "Daftar Buku",
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
            IconButton(onClick = { viewModel.getBuku() }) {
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
fun BukuStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Buku) -> Unit = {},
    onDetailClick: (String) -> Unit,
    onBackClick: () -> Unit = {}
) {
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeUiState.Success ->
            if (homeUiState.buku.isEmpty()) {
                EmptyBukuView(modifier = modifier)
            } else {
                BukuLayout(
                    buku = homeUiState.buku,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.idBuku.toString()) },
                    onDeleteClick = { onDeleteClick(it) },
                    onBackClick = onBackClick
                )
            }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun EmptyBukuView(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Tidak ada data buku", style = MaterialTheme.typography.bodyMedium)
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
            Text(text = stringResource(R.string.loading_failed), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun BukuLayout(
    buku: List<Buku>,
    modifier: Modifier = Modifier,
    onDetailClick: (Buku) -> Unit,
    onDeleteClick: (Buku) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.background(color = colorResource(id = R.color.white)),
       contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(buku) { buku ->
            BukuCard(
                buku = buku,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(buku) },
                onDeleteClick = { onDeleteClick(buku) },
                //onBackClick = onBackClick
            )
        }
    }
}

@Composable
fun BukuCard(
    buku: Buku,
    modifier: Modifier = Modifier,
    onDeleteClick: (Buku) -> Unit = {},
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
                // Ganti ikon back dengan ikon buku
                Icon(
                    painter = painterResource(id = R.drawable.bukuputih), // Ganti dengan drawable buku yang sesuai
                    contentDescription = "Buku",
                    tint = Color.White
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = buku.namaBuku,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(buku) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Buku",
                        tint = Color.White
                        //tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            Divider()
            Column {
                Text(
                    text = "Deskripsi: ${buku.deskripsiBuku}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Tanggal Terbit: ${buku.tanggalTerbit}",
                    style = MaterialTheme.typography.bodyMedium,
                    color  = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Status: ${buku.statusBuku}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
