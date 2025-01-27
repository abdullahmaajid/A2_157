package com.example.projectakhir.data.container

import com.example.projectakhir.data.service.BukuService
import com.example.projectakhir.data.service.KategoriService
import com.example.projectakhir.data.service.PenulisService
import com.example.projectakhir.data.service.PenerbitService
import com.example.projectakhir.data.repository.BukuRepository
import com.example.projectakhir.data.repository.KategoriRepository
import com.example.projectakhir.data.repository.PenulisRepository
import com.example.projectakhir.data.repository.PenerbitRepository
import com.example.projectakhir.data.repository.NetworkBukuRepository
import com.example.projectakhir.data.repository.NetworkKategoriRepository
import com.example.projectakhir.data.repository.NetworkPenulisRepository
import com.example.projectakhir.data.repository.NetworkPenerbitRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bukuRepository: BukuRepository
    val kategoriRepository: KategoriRepository
    val penulisRepository: PenulisRepository
    val penerbitRepository: PenerbitRepository
}

class PerpustakaanContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/" // Sesuaikan dengan URL API Anda

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()




    private val bukuService: BukuService by lazy {
        retrofit.create(BukuService::class.java)
    }

    private val kategoriService: KategoriService by lazy {
        retrofit.create(KategoriService::class.java)
    }

    private val penulisService: PenulisService by lazy {
        retrofit.create(PenulisService::class.java)
    }

    private val penerbitService: PenerbitService by lazy {
        retrofit.create(PenerbitService::class.java)
    }





    override val bukuRepository: BukuRepository by lazy {
        NetworkBukuRepository(bukuService)
    }


    override val kategoriRepository: KategoriRepository by lazy {
        NetworkKategoriRepository(kategoriService)
    }

    override val penulisRepository: PenulisRepository by lazy {
        NetworkPenulisRepository(penulisService)
    }

    override val penerbitRepository: PenerbitRepository by lazy {
        NetworkPenerbitRepository(penerbitService)
    }
}

//object BioskopContainer {
//    private val baseUrl = "http://10.0.2.2:3001/api/"
//    private val json = Json { ignoreUnknownKeys = true }
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//        .baseUrl(baseUrl)
//        .build()
//
//    // Service untuk Film
//    private val filmService: FilmService by lazy {
//    retrofit.create(FilmService::class.java) }
//
//    // Repository untuk Film
//    val filmRepository: FilmRepository by lazy {
//    NetworkFilmRepository(filmService) }
//
//    // Service untuk Studio
//    private val studioService: StudioService by lazy {
//    retrofit.create(StudioService::class.java) }
//
//    // Repository untuk Studio
//    val studioRepository: StudioRepository by lazy {
//    NetworkStudioRepository(studioService) }
//
//    // Service untuk Penayangan
//    private val penayanganService: PenayanganService by lazy {
//    retrofit.create(PenayanganService::class.java) }
//
//    // Repository untuk Penayangan
//    val penayanganRepository: PenayanganRepository by lazy {
//        NetworkPenayanganRepository(
//            penayanganService = penayanganService,
//            filmRepository = filmRepository, // Berikan nilai untuk filmRepository
//            studioRepository = studioRepository // Berikan nilai untuk studioRepository
//        )
//    }
//}