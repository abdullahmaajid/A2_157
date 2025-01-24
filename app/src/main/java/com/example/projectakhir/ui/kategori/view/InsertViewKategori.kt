package com.example.projectakhir.ui.kategori.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.kategori.viewmodel.InsertViewModelKategori
import com.example.projectakhir.ui.kategori.viewmodel.KategoriUiEvent
import com.example.projectakhir.ui.kategori.viewmodel.KategoriUiState1
import kotlinx.coroutines.launch

//object DestinasiEntryKategori : DestinasiNavigasi {
//    override val route = "kategori_entry"
//    override val titleRes = "Entry Kategori"
//}

object DestinasiEntryKategori : DestinasiNavigasi {
    override val route = "entryKategori"
    override val titleRes = "Tambah Kategori"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertViewKategori(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryKategori.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryKategoriBody(
            kategoriUiState1 = viewModel.uiState,
            onKategoriValueChange = viewModel::updateInsertKtgrState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKtgr()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryKategoriBody(
    kategoriUiState1: KategoriUiState1,
    onKategoriValueChange: (KategoriUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputKategori(
            kategoriUiEvent = kategoriUiState1.kategoriUiEvent,
            onValueChange = onKategoriValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(text = "Simpan", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputKategori(
    kategoriUiEvent: KategoriUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (KategoriUiEvent) -> Unit,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ID Kategori TextField


        // Nama Kategori TextField
        OutlinedTextField(
            value = kategoriUiEvent.namaKategori,
            onValueChange = { onValueChange(kategoriUiEvent.copy(namaKategori = it)) },
            label = { Text("Nama Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            colors = MaterialTheme.colorScheme.run {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primary,
                    unfocusedBorderColor = onSurfaceVariant.copy(alpha = 0.5f)
                )
            }
        )

        // Deskripsi Kategori TextField
        OutlinedTextField(
            value = kategoriUiEvent.deskripsiKategori,
            onValueChange = { onValueChange(kategoriUiEvent.copy(deskripsiKategori = it)) },
            label = { Text("Deskripsi Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            colors = MaterialTheme.colorScheme.run {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primary,
                    unfocusedBorderColor = onSurfaceVariant.copy(alpha = 0.5f)
                )
            }
        )

        // Instructions Text
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(12.dp)
            )
        }

        // Divider with softer color
        Divider(
            thickness = 1.dp,
            modifier = Modifier.padding(12.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    }
}