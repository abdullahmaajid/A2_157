package com.example.projectakhir.data.applications


import android.app.Application
import com.example.projectakhir.data.container.AppContainer
import com.example.projectakhir.data.container.PerpustakaanContainer

class PerpustakaanApplications : Application() {
    // Properti untuk menyimpan instance container
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Inisialisasi container saat aplikasi dimulai
        container = PerpustakaanContainer()
    }
}