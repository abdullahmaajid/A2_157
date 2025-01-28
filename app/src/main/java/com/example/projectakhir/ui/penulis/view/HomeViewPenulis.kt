package com.example.projectakhir.ui.penulis.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projectakhir.R
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.penulis.viewmodel.HomeViewModelPenulis
import com.example.projectakhir.ui.penulis.viewmodel.PenulisUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewPenulis(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    navController: NavController,
    onBackClick: () -> Unit = {},
    viewModel: HomeViewModelPenulis = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PenulisTopBar(navController = navController, viewModel = viewModel, scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            PenulisFab(onClick = navigateToItemEntry)
        },
    ) { innerPadding ->
        PenulisStatus(
            penulisUiState = viewModel.penulisUiState,
            retryAction = { viewModel.getPenulis() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletePenulis(it.idPenulis)
                viewModel.getPenulis()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PenulisTopBar(
    navController: NavController,
    viewModel: HomeViewModelPenulis,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = Modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.white)
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Daftar Penulis",
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
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(onClick = { viewModel.getPenulis() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    tint = Color.Black
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun PenulisFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp),
        containerColor = colorResource(id = R.color.white)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Penulis",
            tint = Color.Black
        )
    }
}

@Composable
fun PenulisStatus(
    penulisUiState: PenulisUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penulis) -> Unit = {},
    onDetailClick: (Int) -> Unit
) {
    when (penulisUiState) {
        is PenulisUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is PenulisUiState.Success -> {
            if (penulisUiState.penulis.isEmpty()) {
                EmptyPenulisView(modifier = modifier)
            } else {
                PenulisLayout(
                    penulis = penulisUiState.penulis,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.idPenulis) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }
        is PenulisUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}



@Composable
fun EmptyPenulisView(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Tidak ada data penulis", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun PenulisLayout(
    penulis: List<Penulis>,
    modifier: Modifier = Modifier,
    onDetailClick: (Penulis) -> Unit,
    onDeleteClick: (Penulis) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.background(color = colorResource(id = R.color.white)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(penulis) { penulis ->
            PenulisCard(
                penulis = penulis,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(penulis) },
                onDeleteClick = { onDeleteClick(penulis) }
            )
        }
    }
}





@Composable
fun PenulisCard(
    penulis: Penulis,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penulis) -> Unit = {},
    onEditClick: (Penulis) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
            pressedElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.black)
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = penulis.namaPenulis,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))

                IconButton(onClick = { onDeleteClick(penulis) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Penulis",
                        tint = Color.White
                    )
                }

                IconButton(onClick = { onEditClick(penulis) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Penulis",
                        tint = Color.White
                    )
                }
            }

            Divider()

            Column {
                Text(
                    text = "ID Penulis: ${penulis.idPenulis}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = "Biografi: ${penulis.biografi}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = "Kontak: ${penulis.kontak}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
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
