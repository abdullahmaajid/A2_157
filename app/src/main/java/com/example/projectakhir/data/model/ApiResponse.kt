package com.example.projectakhir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(     //T adalah singkatan dari "Type"
    val status: Boolean,      // Status respons (true/false)
    val message: String,      // Pesan dari server
    val data: T               // Data (bisa berupa objek atau list)
)
@Serializable
data class ApiResponseSingle<T>( //T adalah singkatan dari "Type"
    val status: Boolean,      // Status respons (true/false)
    val message: String,      // Pesan dari server
    val data: T               // Data (objek tunggal)
)
