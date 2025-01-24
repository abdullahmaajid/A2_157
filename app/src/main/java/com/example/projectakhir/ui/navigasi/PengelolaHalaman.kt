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
import com.example.projectakhir.ui.penerbit.view.DestinasiDetailPenerbit
import com.example.projectakhir.ui.penerbit.view.DetailViewPenerbit
import com.example.projectakhir.ui.penerbit.view.HomeViewPenerbit
import com.example.projectakhir.ui.penerbit.view.InsertViewPenerbit
import com.example.projectakhir.ui.penerbit.view.UpdateViewPenerbit
import com.example.projectakhir.ui.penulis.view.DetailViewPenulis
import com.example.projectakhir.ui.penulis.view.HomeViewPenulis
import com.example.projectakhir.ui.penulis.view.InsertViewPenulis
import com.example.projectakhir.ui.penulis.view.UpdateViewPenulis

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


            //======================================   BUKU   ====================================//
            composable(route = DestinasiHomeBuku.route) {
                HomeViewBuku(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryBuku.route) },
                )
            }




            //====================================   KATEGORI   ==================================//
            // GET KATEGORI
            composable(route = DestinasiHomeKategori.route) {
                //HomeViewBuku(
                HomeViewKategori(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryKategori.route) },
                )
            }
            // Insert KATEGORI
            composable(route = DestinasiEntryKategori.route){
                InsertViewKategori(navigateBack = {
                    navController.navigate(DestinasiHomeKategori.route){
                        popUpTo(DestinasiHomeKategori.route){
                            inclusive = true
                        }
                    }
                })
            }
            // DETAIL KATEGORI
            composable(route = DestinasiHomeKategori.route) {
                HomeViewKategori(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryKategori.route) },
                    onDetailClick = { idKategori ->
                        navController.navigate("${DestinasiDetailKategori.route}/$idKategori")
                    }
                )
            }
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
            // UPDATE KATEGORI
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







            //===================================   PENULIS   ====================================//
            composable(route = DestinasiHomePenulis.route) {
                //HomeViewBuku(
                HomeViewPenulis(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryPenulis.route) },
                )
            }
         //Insert PENULIS
            composable(route = DestinasiEntryPenulis.route){
               InsertViewPenulis(navigateBack = {
                    navController.navigate(DestinasiHomePenulis.route){
                        popUpTo(DestinasiHomePenulis.route){
                            inclusive = true
                        }
                    }
                })
            }

// DETAIL PENULIS
            composable(route = DestinasiHomePenulis.route) {
                HomeViewPenulis(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryPenulis.route) },
                    onDetailClick = { idPenulis ->
                        navController.navigate("${DestinasiDetailPenulis.route}/$idPenulis")
                    }
                )
            }
            composable(
                route = DestinasiDetailPenulis.routeWithArgument,
                arguments = listOf(
                    navArgument(DestinasiDetailPenulis.idPenulisArg) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val idPenulis = backStackEntry.arguments?.getInt(DestinasiDetailPenulis.idPenulisArg)
                idPenulis?.let { id ->
                    DetailViewPenulis(
                        navigateBack = {
                            navController.navigate(DestinasiHomePenulis.route) {
                                popUpTo(DestinasiHomePenulis.route) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToEdit = {
                            navController.navigate("${DestinasiUpdatePenulis.route}/$idPenulis")
                        }
                    )
                }
            }
//UPDATE PENULIS
           composable(
                DestinasiUpdatePenulis.routeWithArgument,
                arguments = listOf(navArgument(DestinasiUpdatePenulis.idPenulisArg) {
                    type = NavType.IntType
                })
            ) {
                val idPenulis = it.arguments?.getInt(DestinasiUpdatePenulis.idPenulisArg)
                idPenulis?.let { id ->
                    UpdateViewPenulis(
                        onBack = { navController.popBackStack() },
                        onNavigate = { navController.popBackStack() }
                    )
                }
            }









            //===================================   PENERBIT   ====================================//
            // GET PENERBIT
            composable(route = DestinasiHomePenerbit.route) {
                //HomeViewBuku(
                HomeViewPenerbit(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryPenerbit.route) },
                )
            }
// Insert PENERBIT
            composable(route = DestinasiEntryPenerbit.route){
                InsertViewPenerbit(navigateBack = {
                    navController.navigate(DestinasiHomePenerbit.route){
                        popUpTo(DestinasiHomePenerbit.route){
                            inclusive = true
                        }
                    }
                })
            }
// DETAIL PENERBIT
            composable(route = DestinasiHomePenerbit.route) {
                HomeViewPenerbit(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryPenerbit.route) },
                    onDetailClick = { idPenerbit ->
                        navController.navigate("${DestinasiDetailPenerbit.route}/$idPenerbit")
                    }
                )
            }
            composable(
                route = DestinasiDetailPenerbit.routeWithArgument,
                arguments = listOf(
                    navArgument(DestinasiDetailPenerbit.idPenerbitArg) {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val idPenerbit = backStackEntry.arguments?.getInt(DestinasiDetailPenerbit.idPenerbitArg)
                idPenerbit?.let { id ->
                    DetailViewPenerbit(
                        navigateBack = {
                            navController.navigate(DestinasiHomePenerbit.route) {
                                popUpTo(DestinasiHomePenerbit.route) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToEdit = {
                            navController.navigate("${DestinasiUpdatePenerbit.route}/$idPenerbit")
                        }
                    )
                }
            }
// UPDATE PENERBIT
            composable(
                DestinasiUpdatePenerbit.routeWithArgument,
                arguments = listOf(navArgument(DestinasiUpdatePenerbit.idPenerbitArg) {
                    type = NavType.IntType
                })
            ) {
                val idPenerbit = it.arguments?.getInt(DestinasiUpdatePenerbit.idPenerbitArg)
                idPenerbit?.let { id ->
                    UpdateViewPenerbit(
                        onBack = { navController.popBackStack() },
                        onNavigate = { navController.popBackStack() }
                    )
                }
            }















        }
    }
}