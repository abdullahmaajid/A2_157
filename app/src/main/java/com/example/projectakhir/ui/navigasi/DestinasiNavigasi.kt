package com.example.projectakhir.ui.navigasi

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiMainScreen : DestinasiNavigasi {
    override val route = "mainScreen"
    override val titleRes = "Main Screen"
}




object DestinasiHomeBuku : DestinasiNavigasi {
    override val route = "homeBuku"
    override val titleRes = "Daftar Buku"
}



object DestinasiEntryBuku : DestinasiNavigasi {
    override val route = "entryBuku"
    override val titleRes = "Tambah Buku"
}


object DestinasiDetailBuku : DestinasiNavigasi {
    override val route = "detailBuku"
    override val titleRes = "Detail Buku"
    const val routeWithArgument = "detailBuku/{idBuku}"
    const val idBukuArg = "idBuku"
}

object DestinasiUpdateBuku : DestinasiNavigasi {
    override val route = "editBuku"
    override val titleRes = "Edit Buku"
    const val routeWithArgument = "editBuku/{idBuku}"
    const val idBukuArg = "idBuku"
}










//object DestinasiDeleteBuku : DestinasiNavigasi {
//    override val route = "deleteBuku"
//    override val titleRes = "Hapus Buku"
//    const val routeWithArgument = "deleteBuku/{idBuku}"
//    const val idBukuArg = "idBuku"
//}




object DestinasiHomeKategori : DestinasiNavigasi {
    override val route = "homeKategori"
    override val titleRes = "Daftar Kategori"
}

object DestinasiEntryKategori : DestinasiNavigasi {
    override val route = "entryKategori"
    override val titleRes = "Tambah Kategori"
}

object DestinasiDetailKategori : DestinasiNavigasi {
    override val route = "detailKategori"
    override val titleRes = "Detail Kategori"
    const val routeWithArgument = "detailKategori/{idKategori}"
    const val idKategoriArg = "idKategori"


}

object DestinasiUpdateKategori : DestinasiNavigasi {
    override val route = "editKategori"
    override val titleRes = "Edit Kategori"
    const val routeWithArgument = "editKategori/{idKategori}"
    const val idKategoriArg = "idKategori"
}



//object DestinasiDeleteKategori : DestinasiNavigasi {
//    override val route = "deleteKategori"
//    override val titleRes = "Hapus Kategori"
//    const val routeWithArgument = "deleteKategori/{idKategori}"
//    const val idKategoriArg = "idKategori"
//}


object DestinasiHomePenulis : DestinasiNavigasi {
    override val route = "homePenulis"
    override val titleRes = "Daftar Penulis"
}

object DestinasiEntryPenulis : DestinasiNavigasi {
    override val route = "entryPenulis"
    override val titleRes = "Tambah Penulis"
}

object DestinasiDetailPenulis : DestinasiNavigasi {
    override val route = "detailPenulis"
    override val titleRes = "Detail Penulis"
    const val routeWithArgument = "detailPenulis/{idPenulis}"
    const val idPenulisArg = "idPenulis"
}

object DestinasiUpdatePenulis : DestinasiNavigasi {
    override val route = "editPenulis"
    override val titleRes = "Edit Penulis"
    const val routeWithArgument = "editPenulis/{idPenulis}"
    const val idPenulisArg = "idPenulis"
}

object DestinasiDeletePenulis : DestinasiNavigasi {
    override val route = "deletePenulis"
    override val titleRes = "Hapus Penulis"
    const val routeWithArgument = "deletePenulis/{idPenulis}"
    const val idPenulisArg = "idPenulis"
}


object DestinasiHomePenerbit : DestinasiNavigasi {
    override val route = "homePenerbit"
    override val titleRes = "Daftar Penerbit"
}

object DestinasiEntryPenerbit : DestinasiNavigasi {
    override val route = "entryPenerbit"
    override val titleRes = "Tambah Penerbit"
}

object DestinasiDetailPenerbit : DestinasiNavigasi {
    override val route = "detailPenerbit"
    override val titleRes = "Detail Penerbit"
    const val routeWithArgument = "detailPenerbit/{idPenerbit}"
    const val idPenerbitArg = "idPenerbit"
}

object DestinasiUpdatePenerbit : DestinasiNavigasi {
    override val route = "editPenerbit"
    override val titleRes = "Edit Penerbit"
    const val routeWithArgument = "editPenerbit/{idPenerbit}"
    const val idPenerbitArg = "idPenerbit"
}

//object DestinasiDeletePenerbit : DestinasiNavigasi {
//    override val route = "deletePenerbit"
//    override val titleRes = "Hapus Penerbit"
//    const val routeWithArgument = "deletePenerbit/{idPenerbit}"
//    const val idPenerbitArg = "idPenerbit"
//}





