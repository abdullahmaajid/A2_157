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
import com.example.projectakhir.ui.penerbit.view.InsertViewPenerbit
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
            // DELETE KATEGORI
            composable(route = DestinasiHomeKategori.route) {
                HomeViewKategori(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryKategori.route) },
                    onDetailClick = { idKategori ->
                        navController.navigate("${DestinasiDetailKategori.route}/$idKategori")
                    }
                )
            }
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


            //===================================   PENERBIT   ====================================//
            // Get Penerbit
            composable(route = DestinasiHomePenerbit.route) {
                //HomeViewBuku (
                HomeViewPenerbit(
                    navController = navController,
                    navigateToItemEntry = { navController.navigate(DestinasiEntryPenerbit.route) },
                )
            }
            // Insert Penerbit
            composable(route = DestinasiEntryPenerbit.route){
                InsertViewPenerbit(navigateBack = {
                    navController.navigate(DestinasiHomePenerbit.route){
                        popUpTo(DestinasiHomePenerbit.route){
                            inclusive = true
                        }
                    }
                })
            }












        }
    }
}