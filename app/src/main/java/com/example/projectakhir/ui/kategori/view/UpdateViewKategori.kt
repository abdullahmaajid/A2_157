package com.example.projectakhir.ui.kategori.view


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.ui.PenyediaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiUpdateKategori
import com.example.projectakhir.ui.kategori.viewmodel.UpdateViewModelKategori
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateKategori : DestinasiNavigasi {
    override val route = "update_kategori"
    override val titleRes = "Update Kategori"
    const val idKategoriArg = "idKategori"
    val routeWithArg = "$route/{$idKategoriArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateViewKategori(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateKategori.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        EntryKategoriBody(
            modifier = Modifier.padding(padding),
            kategoriUiState1 = viewModel.updateUiState,
            onKategoriValueChange = viewModel::updateInsertKtgrState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKategori()
                    delay(600) // Wait for update operation to complete
                    withContext(Dispatchers.Main) {
                        onNavigate() // Navigate to the desired destination
                    }
                }
            }
        )
    }
}
