package com.example.projectakhir.ui.navigasi


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectakhir.ui.HomeApp
import com.example.projectakhir.ui.buku.view.HomeViewBuku
import com.example.projectakhir.ui.kategori.view.DetailViewKategori
import com.example.projectakhir.ui.kategori.view.HomeViewKategori
import com.example.projectakhir.ui.kategori.view.InsertViewKategori
import com.example.projectakhir.ui.kategori.view.UpdateViewKategori
import com.example.projectakhir.ui.navigasi.DestinasiHomeBuku
import com.example.projectakhir.ui.navigasi.DestinasiMainScreen
//import com.example.projectakhir.ui.MainScreen
//import com.example.projectakhir.ui.buku.view.HomeViewBuku
import com.example.projectakhir.ui.kategori.viewmodel.HomeViewModelKategori
import com.example.projectakhir.ui.penerbit.view.HomeViewPenerbit
import com.example.projectakhir.ui.penulis.view.HomeViewPenulis

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = DestinasiMainScreen.route,
            modifier = modifier.padding(it) // Ensure content is above the navigation bar
        ) {
            // Main Screen
            composable(route = DestinasiMainScreen.route) {
                HomeApp(
                    onHalamanBuku = {
                        navController.navigate(DestinasiHomeBuku.route)
                    },
                    onHalamanKategori = {
                        navController.navigate(DestinasiHomeKategori.route)
                    },
                    onHalamanPenulis = {
                        navController.navigate(DestinasiHomePenulis.route)
                    },
                    onHalamanPenerbit = {
                        navController.navigate(DestinasiHomePenerbit.route)
                    },
                    modifier = modifier
                )
            }

            composable(route = DestinasiHomeBuku.route) {
                HomeViewBuku(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryBuku.route) },
                )
            }

            composable(route = DestinasiHomeKategori.route) {
                //HomeViewBuku(
                HomeViewKategori(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryKategori.route) },
                )
            }

            composable(route = DestinasiHomePenulis.route) {
                //HomeViewBuku(
                HomeViewPenulis(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryPenulis.route) },
                )
            }

            composable(route = DestinasiHomePenerbit.route) {
                //HomeViewBuku (
                HomeViewPenerbit(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryPenerbit.route) },
                )
            }







        composable(route = DestinasiEntryKategori.route){
            InsertViewKategori(navigateBack = {
                navController.navigate(DestinasiHomeKategori.route){
                    popUpTo(DestinasiHomeKategori.route){
                        inclusive = true
                    }
                }
            })
        }



            // TAMBAH KATEGORI
            composable(route = DestinasiHomeKategori.route) {
                HomeViewKategori(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryKategori.route) },
                    onDetailClick = { idKategori ->
                        navController.navigate("${DestinasiDetailKategori.route}/$idKategori")
                    }
                )
            }



//            //// Define navigation object for Kategori details
////object DestinasiDetailKategori : DestinasiNavigasi {
////    override val route = "detail_kategori"
////    const val ID_KATEGORI = "id_kategori"
////    override val titleRes = "Detail Kategori"
////    val routeWithArg = "$route/{$ID_KATEGORI}"
////}
//
//            object DestinasiDetailKategori : DestinasiNavigasi {
//                override val route = "detailKategori"
//                const val idKategoriArg = "idKategori"
//                override val titleRes = "Detail Kategori"
//                const val routeWithArgument = "detailKategori/{idKategori}"
//            }

            // DETAIL KATEGORI
            composable(
                route = DestinasiDetailKategori.routeWithArgument,
                arguments = listOf(
                    navArgument(DestinasiDetailKategori.idKategoriArg) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val idKategori = backStackEntry.arguments?.getInt(DestinasiDetailKategori.idKategoriArg)
                idKategori?.let { id ->
                    DetailViewKategori(
                        navigateBack = {
                            navController.navigate(DestinasiHomeKategori.route) {
                                popUpTo(DestinasiHomeKategori.route) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToEdit = {
                            navController.navigate("${DestinasiUpdateKategori.route}/$idKategori")
                        }
                    )
                }
            }

            composable(
                DestinasiUpdateKategori.routeWithArgument,
                arguments = listOf(navArgument(DestinasiUpdateKategori.idKategoriArg) {
                    type = NavType.IntType
                })
            ) {
                val idKategori = it.arguments?.getInt(DestinasiUpdateKategori.idKategoriArg)
                idKategori?.let { id ->
                    UpdateViewKategori(
                        onBack = { navController.popBackStack() },
                        onNavigate = { navController.popBackStack() }
                    )
                }
            }



//            composable(
//                route = DestinasiDetailKategori.routeWithArgument,
//                arguments = listOf(
//                    navArgument(DestinasiDetailKategori.idKategoriArg) { type = NavType.IntType }
//                )
//            ) { backStackEntry ->
//                // Retrieve the argument from the navigation route
//                val idKategori = backStackEntry.arguments?.getInt(DestinasiDetailKategori.ID_KATEGORI)
//                requireNotNull(idKategori) { "ID Kategori is required for this route" }
//
//                // Initialize ViewModel with SavedStateHandle
//                val viewModel: DetailViewModelKategori = viewModel(
//                    factory = PenyediaViewModel.Factory
//                )
//
//                // Pass navigation actions
//                DetailViewKategori(
//                    navigateBack = { navController.popBackStack() },
//                    navigateToEdit = {
//                        navController.navigate("edit_kategori/$idKategori")
//                    }
//                )
//            }






            // Nonaktifkan sementara
            /*
        composable(route = DestinasiEntryBuku.route) {
            EntryViewBuku(
                onBackClick = { navController.popBackStack() },
                onSaveClick = {
                    // Logika untuk menyimpan buku
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = DestinasiDetailBuku.routeWithArgument,
            arguments = listOf(navArgument(DestinasiDetailBuku.idBukuArg) { type = NavType.IntType })
        ) { backStackEntry ->
            val idBuku = backStackEntry.arguments?.getInt(DestinasiDetailBuku.idBukuArg)
            idBuku?.let {
                DetailViewBuku(
                    idBuku = it,
                    onBackClick = { navController.popBackStack() },
                    onEditClick = { idBuku ->
                        navController.navigate("${DestinasiEditBuku.route}/$idBuku")
                    }
                )
            }
        }

        composable(
            route = DestinasiEditBuku.routeWithArgument,
            arguments = listOf(navArgument(DestinasiEditBuku.idBukuArg) { type = NavType.IntType })
        ) { backStackEntry ->
            val idBuku = backStackEntry.arguments?.getInt(DestinasiEditBuku.idBukuArg)
            idBuku?.let {
                EditViewBuku(
                    idBuku = it,
                    onBackClick = { navController.popBackStack() },
                    onSaveClick = {
                        // Logika untuk menyimpan perubahan buku
                        navController.popBackStack()
                    }
                )
            }
        }
        */
        }
    }
}