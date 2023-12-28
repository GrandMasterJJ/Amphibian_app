package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApiService
import com.example.amphibians.network.AmphibianPhoto


interface AmphibianPhotoRepository {
    suspend fun getAmphibianPhotos(): List<AmphibianPhoto>
    suspend fun getAmphibianDescription(): List<AmphibianPhoto>
}

class NetWorkAmphibianPhotoRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianPhotoRepository{
    override suspend fun getAmphibianPhotos(): List<AmphibianPhoto> = amphibianApiService.getPhotos()
    override suspend fun getAmphibianDescription(): List<AmphibianPhoto> = amphibianApiService.getDescription()
}