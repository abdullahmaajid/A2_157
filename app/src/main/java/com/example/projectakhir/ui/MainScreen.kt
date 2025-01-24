package com.example.projectakhir.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectakhir.R

@Composable
fun HomeApp(
    modifier: Modifier = Modifier,
    onHalamanBuku: () -> Unit,
    onHalamanKategori: () -> Unit,
    onHalamanPenulis: () -> Unit,
    onHalamanPenerbit: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 35.dp),
        verticalArrangement = Arrangement.Top
    // Membuat latar belakang hitam untuk seluruh layar
    ) {
        HeaderSection()
        BodySection(
            onHalamanBuku = onHalamanBuku,
            onHalamanKategori = onHalamanKategori,
            onHalamanPenulis = onHalamanPenulis,
            onHalamanPenerbit = onHalamanPenerbit
        )
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 48.dp))
            .background(color = Color.Black) // Gelap lebih pekat daripada 0xFF1A1A1A
           .padding(bottom = 25.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 1.dp, top = 25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.umy),
                contentDescription = "UMY Logo Left",
                modifier = Modifier
                    .padding(5.dp)
                    .size(90.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "Perpustakaan App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.weight(1f))
        }
    }
}


@Composable
fun BodySection(
    onHalamanBuku: () -> Unit,
    onHalamanKategori: () -> Unit,
    onHalamanPenulis: () -> Unit,
    onHalamanPenerbit: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Silahkan pilih menu yang ingin anda kelola",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                ManageBox(
                    title = "Buku",
                    description = "Kelola Daftar Buku",
                    backgroundColor = Color.Black, // Darker box background
                    iconResource = R.drawable.bukuputih,
                    onClick = { onHalamanBuku() }
                )
                Spacer(Modifier.height(16.dp))
                ManageBox(
                    title = "Penulis",
                    description = "Kelola Penulis Buku",
                    backgroundColor = Color.Black,
                    iconResource = R.drawable.penulisputih,
                    onClick = { onHalamanPenulis() }
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                ManageBox(
                    title = "Kategori",
                    description = "Kelola Kategori Buku",
                    backgroundColor = Color.Black,
                    iconResource = R.drawable.kategoriputih,
                    onClick = { onHalamanKategori() }
                )
                Spacer(Modifier.height(16.dp))
                ManageBox(
                    title = "Penerbit",
                    description = "Kelola Penerbit Buku",
                    backgroundColor = Color.Black,
                    iconResource = R.drawable.penerbitputih,
                    onClick = { onHalamanPenerbit() }
                )
            }
        }
    }
}

@Composable
fun ManageBox(
    title: String,
    description: String,
    backgroundColor: Color,
    iconResource: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp) // Menambahkan jarak antar elemen vertikal
        ) {
            // Ikon di atas
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(50.dp) // Sesuaikan ukuran ikon jika diperlukan
                    .clip(CircleShape)
            )

            // Judul di bawah ikon
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            // Deskripsi di bawah judul
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color(0xFFE0E0E0) // Warna abu-abu untuk deskripsi
            )
        }
    }
}

