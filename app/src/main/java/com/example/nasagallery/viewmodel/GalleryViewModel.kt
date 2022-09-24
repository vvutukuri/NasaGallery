package com.example.nasagallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasagallery.repository.GalleryRepository
import com.example.nasagallery.utils.GetImagesEvent
import com.example.nasagallery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val galleryRepository: GalleryRepository):ViewModel() {
    private val _imagesMutableLiveData = MutableLiveData<GetImagesEvent>()
    val imagesList: LiveData<GetImagesEvent> = _imagesMutableLiveData
    fun getImagesListData(){
        var galleryResponseData=galleryRepository.getImagesData()
        when(galleryResponseData){
            is Resource.Success ->{
                _imagesMutableLiveData.postValue(GetImagesEvent.Success(galleryResponseData.data!!))
            }
            is Resource.Error ->{
                _imagesMutableLiveData.postValue(GetImagesEvent.Failure(galleryResponseData.errorMsg?:""))
            }
        }
    }


}