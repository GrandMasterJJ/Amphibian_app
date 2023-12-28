package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibianPhotoApplication
import com.example.amphibians.data.AmphibianPhotoRepository
import com.example.amphibians.data.DefaultAppContainer
import com.example.amphibians.data.NetWorkAmphibianPhotoRepository
//import com.example.amphibians.network.AmphibianApi
import com.example.amphibians.network.AmphibianPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibianUiState {
    // Change the data class to Object to create the objects for the web responses.
    //data class Success(val photos: AmphibianPhoto) : AmphibianUiState
    //data class Success(val photos: String) : AmphibianUiState
    data class Success(val photos: List<AmphibianPhoto>) : AmphibianUiState

    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}

class AmphibianViewModel(private val amphibianPhotoRepository: AmphibianPhotoRepository) : ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibiansPhotos()
    }

    fun getAmphibiansPhotos() {
        viewModelScope.launch{
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try{
                //val marsPhotoRepository = NetWorkAmphibianPhotoRepository()
                //val listResult = marsPhotoRepository.getAmphibianPhotos()
                //AmphibianUiState.Success("${listResult.size}")
                //val listResult = amphibianPhotoRepository.getAmphibianPhotos()[0]
                //AmphibianUiState.Success("${listResult.imgSrc}")
                AmphibianUiState.Success(amphibianPhotoRepository.getAmphibianPhotos())

            }catch (e: IOException){
                AmphibianUiState.Error
            }
            catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }

    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {

            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianPhotoApplication)
                val amphibianPhotoRepository = application.container.amphibianPhotoRepository
                //AmphibianViewModel(amphibianPhotoRepository = amphibianPhotoRepository)
                AmphibianViewModel(amphibianPhotoRepository = amphibianPhotoRepository) // amphibianPhotoRepository = amphibianPhotoRepository
            }


        }
    }
}