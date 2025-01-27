package com.example.projectakhir.ui.penerbit.view


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
import com.example.projectakhir.ui.navigasi.DestinasiUpdatePenerbit
import com.example.projectakhir.ui.penerbit.viewmodel.UpdateViewModelPenerbit
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdatePenerbit : DestinasiNavigasi {
    override val route = "updatePenerbit"
    override val titleRes = "Update Penerbit"
    const val idPenerbitArg = "idPenerbit"
    val routeWithArg = "$route/{$idPenerbitArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateViewPenerbit(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateViewModelPenerbit = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdatePenerbit.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        EntryPenerbitBody(
            modifier = Modifier.padding(padding),
            penerbitUiState1 = viewModel.updateUiState,
            onPenerbitValueChange = viewModel::updateInsertPnrbtState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePenerbit()
                    delay(6) // Wait for update operation to complete
                    withContext(Dispatchers.Main) {
                        onNavigate() // Navigate to the desired destination
                    }
                }
            }
        )
    }
}
