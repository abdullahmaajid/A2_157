// Mendeklarasikan package tempat file ini berada
package com.example.projectakhir.data.container

// Import kelas-kelas yang diperlukan untuk layanan (service) data
import com.example.projectakhir.data.service.BukuService
import com.example.projectakhir.data.service.KategoriService
import com.example.projectakhir.data.service.PenulisService
import com.example.projectakhir.data.service.PenerbitService

// Import kelas-kelas yang diperlukan untuk repository data
import com.example.projectakhir.data.repository.BukuRepository
import com.example.projectakhir.data.repository.KategoriRepository
import com.example.projectakhir.data.repository.PenulisRepository
import com.example.projectakhir.data.repository.PenerbitRepository
import com.example.projectakhir.data.repository.NetworkBukuRepository
import com.example.projectakhir.data.repository.NetworkKategoriRepository
import com.example.projectakhir.data.repository.NetworkPenulisRepository
import com.example.projectakhir.data.repository.NetworkPenerbitRepository

// Import untuk konversi JSON ke objek menggunakan kotlinx.serialization
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json

// Import untuk mendeklarasikan tipe media (seperti JSON)
import okhttp3.MediaType.Companion.toMediaType

// Import untuk mendeklarasikan Retrofit sebagai alat HTTP client
import retrofit2.Retrofit

// Interface untuk AppContainer yang mendeklarasikan repository sebagai properti abstrak
interface AppContainer {
    // Deklarasi repository untuk buku
    val bukuRepository: BukuRepository

    // Deklarasi repository untuk kategori
    val kategoriRepository: KategoriRepository

    // Deklarasi repository untuk penulis
    val penulisRepository: PenulisRepository

    // Deklarasi repository untuk penerbit
    val penerbitRepository: PenerbitRepository
}

// Implementasi dari AppContainer menggunakan kelas PerpustakaanContainer
class PerpustakaanContainer : AppContainer {
    // URL dasar untuk API (disesuaikan dengan API lokal atau server Anda)
    private val baseUrl = "http://10.0.2.2:3000/"

    // Konfigurasi JSON dengan pengaturan untuk mengabaikan properti yang tidak dikenali
    private val json = Json { ignoreUnknownKeys = true }

    // Membuat instance Retrofit menggunakan builder pattern
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType())) // Menambahkan converter JSON untuk Retrofit
        .baseUrl(baseUrl) // Mengatur URL dasar API
        .build() // Membangun instance Retrofit

    // Layanan untuk buku, dibuat secara lazy untuk efisiensi
    private val bukuService: BukuService by lazy {
        retrofit.create(BukuService::class.java) // Membuat instance dari BukuService menggunakan Retrofit
    }

    // Layanan untuk kategori, dibuat secara lazy untuk efisiensi
    private val kategoriService: KategoriService by lazy {
        retrofit.create(KategoriService::class.java) // Membuat instance dari KategoriService menggunakan Retrofit
    }

    // Layanan untuk penulis, dibuat secara lazy untuk efisiensi
    private val penulisService: PenulisService by lazy {
        retrofit.create(PenulisService::class.java) // Membuat instance dari PenulisService menggunakan Retrofit
    }

    // Layanan untuk penerbit, dibuat secara lazy untuk efisiensi
    private val penerbitService: PenerbitService by lazy {
        retrofit.create(PenerbitService::class.java) // Membuat instance dari PenerbitService menggunakan Retrofit
    }

    // Repository untuk buku, dibuat secara lazy dan menggunakan NetworkBukuRepository
    override val bukuRepository: BukuRepository by lazy {
        NetworkBukuRepository(bukuService) // Menginisialisasi BukuRepository dengan NetworkBukuRepository
    }

    // Repository untuk kategori, dibuat secara lazy dan menggunakan NetworkKategoriRepository
    override val kategoriRepository: KategoriRepository by lazy {
        NetworkKategoriRepository(kategoriService) // Menginisialisasi KategoriRepository dengan NetworkKategoriRepository
    }

    // Repository untuk penulis, dibuat secara lazy dan menggunakan NetworkPenulisRepository
    override val penulisRepository: PenulisRepository by lazy {
        NetworkPenulisRepository(penulisService) // Menginisialisasi PenulisRepository dengan NetworkPenulisRepository
    }

    // Repository untuk penerbit, dibuat secara lazy dan menggunakan NetworkPenerbitRepository
    override val penerbitRepository: PenerbitRepository by lazy {
        NetworkPenerbitRepository(penerbitService) // Menginisialisasi PenerbitRepository dengan NetworkPenerbitRepository
    }
}

