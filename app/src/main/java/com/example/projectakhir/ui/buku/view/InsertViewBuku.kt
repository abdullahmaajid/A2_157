package com.example.projectakhir.ui.buku.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.data.model.Kategori
import com.example.projectakhir.data.model.Penerbit
import com.example.projectakhir.data.model.Penulis
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.buku.viewmodel.BukuUiEvent
import com.example.projectakhir.ui.buku.viewmodel.BukuUiState
import com.example.projectakhir.ui.buku.viewmodel.InsertViewModelBuku
import com.example.projectakhir.ui.buku.viewmodel.StatusBuku
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import kotlinx.coroutines.launch

object DestinasiEntryBuku {
    const val route = "entryBuku"
    const val titleRes = "Tambah Buku"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertViewBuku(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModelBuku = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchDropdownData() // Load dropdown data at the start
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = "Tambah Buku",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBukuBody(
            uiState = viewModel.uiState,
            kategoriList = viewModel.kategoriList,
            penulisList = viewModel.penulisList,
            penerbitList = viewModel.penerbitList,
            onBukuValueChange = { event -> viewModel.updateBukuState(event)},
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertBuku()
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
fun EntryBukuBody(
    uiState: BukuUiState,
    kategoriList: List<Kategori>,
    penulisList: List<Penulis>,
    penerbitList: List<Penerbit>,
    onBukuValueChange: (BukuUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputBuku(
            bukuUiEvent = uiState.bukuUiEvent,
            kategoriList = kategoriList,
            penulisList = penulisList,
            penerbitList = penerbitList,
            onValueChange = onBukuValueChange,
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

        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun FormInputBuku(
    bukuUiEvent: BukuUiEvent,
    kategoriList: List<Kategori>,
    penulisList: List<Penulis>,
    penerbitList: List<Penerbit>,
    onValueChange: (BukuUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Input fields
        OutlinedTextField(
            value = bukuUiEvent.namaBuku,
            onValueChange = { onValueChange(bukuUiEvent.copy(namaBuku = it)) },
            label = { Text("Nama Buku") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = bukuUiEvent.deskripsiBuku,
            onValueChange = { onValueChange(bukuUiEvent.copy(deskripsiBuku = it)) },
            label = { Text("Deskripsi Buku") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = bukuUiEvent.tanggalTerbit,
            onValueChange = { onValueChange(bukuUiEvent.copy(tanggalTerbit = it)) },
            label = { Text("Tanggal Terbit") },
            modifier = Modifier.fillMaxWidth()
        )
//        OutlinedTextField(
//            value = bukuUiEvent.statusBuku,
//            onValueChange = { onValueChange(bukuUiEvent.copy(harga = it)) },
//            label = { Text("Harga Properti") },
//            modifier = Modifier.fillMaxWidth()
//        )

        // Dropdown untuk status properti
        DropdownMenu(
            items = StatusBuku.values().toList(),
            selectedItem = bukuUiEvent.statusBuku,
            onItemSelected = { status -> onValueChange(bukuUiEvent.copy(statusBuku = status)) },
            label = "Status Buku",
            itemToString = { it.toString() }
        )

        // Dropdown untuk jenis properti
        DropdownMenu(
            items = kategoriList,
            selectedItem = kategoriList.find { it.idKategori == bukuUiEvent.idKategori },
            onItemSelected = { kategori -> onValueChange(bukuUiEvent.copy(idKategori = kategori.idKategori)) },
            label = "Kategori",
            itemToString = { it.namaKategori } // Hanya tampilkan nama_jenis
        )

        // Dropdown untuk penulis
        DropdownMenu(
            items = penulisList,
            selectedItem = penulisList.find { it.idPenulis == bukuUiEvent.idPenulis },
            onItemSelected = { penulis -> onValueChange(bukuUiEvent.copy(idPenulis = penulis.idPenulis)) },
            label = "Penulis",
            itemToString = { it.namaPenulis } // Hanya tampilkan nama_penulis
        )

// Dropdown untuk penerbit
        DropdownMenu(
            items = penerbitList,
            selectedItem = penerbitList.find { it.idPenerbit == bukuUiEvent.idPenerbit },
            onItemSelected = { penerbit -> onValueChange(bukuUiEvent.copy(idPenerbit = penerbit.idPenerbit)) },
            label = "Penerbit",
            itemToString = { it.namaPenerbit } // Hanya tampilkan nama_penerbit
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenu(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    itemToString: (T) -> String = { it.toString() }
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedItem?.let { itemToString(it) } ?: "",
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(), // Menghubungkan TextField dengan DropdownMenu
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(itemToString(item)) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}