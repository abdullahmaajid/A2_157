package com.example.projectakhir.ui.buku.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.buku.viewmodel.UpdateViewModelBuku
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateBuku : DestinasiNavigasi {
    override val route = "editBuku"
    override val titleRes = "Edit Buku"
    const val routeWithArgument = "editBuku/{idBuku}"
    const val idBukuArg = "idBuku"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateViewBuku(
    onBack: () -> Unit, // Navigasi kembali ke halaman sebelumnya
    onNavigate: () -> Unit, // Navigasi ke halaman lain setelah update
    modifier: Modifier = Modifier,
    viewModel: UpdateViewModelBuku = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    // Fetch data saat pertama kali di-load
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = "Edit Buku",
                canNavigateBack = true,
                navigateUp = onBack // Gunakan onBack untuk navigasi kembali
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateBuku()
                        delay(6) // Tunggu proses update selesai
                        withContext(Dispatchers.Main) {
                            onNavigate() // Navigasi ke halaman lain setelah update
                        }
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Update Properti"
                )
            }
        }
    ) { innerPadding ->
        EntryBukuBody(
            uiState = viewModel.uiState,
            kategoriList = viewModel.kategoriList,
            penulisList = viewModel.penulisList,
            penerbitList = viewModel.penerbitList,
            onBukuValueChange = { event -> viewModel.updateBukuState(event) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateBuku()
                    delay(6) // Tunggu proses update selesai
                    withContext(Dispatchers.Main) {
                        onNavigate() // Navigasi ke halaman lain setelah update
                    }
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}