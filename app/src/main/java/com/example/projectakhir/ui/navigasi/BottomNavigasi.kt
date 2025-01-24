package com.example.projectakhir.ui.navigasi

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.projectakhir.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(bottom = 8.dp) // Optional, for better visual effect
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)), // Rounded corners at the top
        containerColor = Color.Black // Menetapkan warna latar belakang menjadi hitam
    ) {
        val items = listOf(
            DestinasiHomeBuku,
            DestinasiHomeKategori,
            DestinasiMainScreen, // Main will be in the center
            DestinasiHomePenulis,
            DestinasiHomePenerbit
        )

        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        // Add items to the left, center, and right
        items.forEachIndexed { index, destination ->
            // If it's the center item (DestinasiMainScreen), apply different layout
            if (index == 2) { // Center item at index 2
                // Center item (Main)
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.homeputih), // Update with your center icon
                            contentDescription = "Main"
                        )
                    },
                    selected = currentDestination?.route == destination.route,
                    onClick = {
                        if (currentDestination?.route != destination.route) {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f) // This makes the center item occupy more space
                )
            } else {
                // For left and right items
                NavigationBarItem(
                    icon = {
                        when (destination) {
                            DestinasiHomeBuku -> Icon(painter = painterResource(id = R.drawable.bukuputih), contentDescription = "Buku")
                            DestinasiHomeKategori -> Icon(painter = painterResource(id = R.drawable.kategoriputih), contentDescription = "Kategori")
                            DestinasiHomePenulis -> Icon(painter = painterResource(id = R.drawable.penulisputih), contentDescription = "Penulis")
                            DestinasiHomePenerbit -> Icon(painter = painterResource(id = R.drawable.penerbitputih), contentDescription = "Penerbit")
                        }
                    },
                    selected = currentDestination?.route == destination.route,
                    onClick = {
                        if (currentDestination?.route != destination.route) {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f) // Make left and right items equally spaced
                )
            }
        }
    }
}
