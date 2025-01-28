package com.example.projectakhir.data.applications


import android.app.Application
import com.example.projectakhir.data.container.AppContainer
import com.example.projectakhir.data.container.PerpustakaanContainer

// Mendeklarasikan kelas utama aplikasi yang mewarisi kelas Application
class PerpustakaanApplications : Application() {

    // Properti untuk menyimpan instance dari AppContainer
    // Menggunakan lateinit agar properti diinisialisasi nanti
    lateinit var container: AppContainer

    // Fungsi override yang akan dipanggil saat aplikasi dimulai
    override fun onCreate() {
        // Memanggil implementasi bawaan dari onCreate di kelas Application
        super.onCreate()

        // Inisialisasi container dengan instance dari PerpustakaanContainer
        container = PerpustakaanContainer()
    }
}
