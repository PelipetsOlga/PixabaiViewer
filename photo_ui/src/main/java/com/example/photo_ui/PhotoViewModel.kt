package com.example.photo_ui

import androidx.lifecycle.ViewModel
import com.example.domain.models.ImageModel
import com.example.domain.models.imageModelEmpty
import com.example.domain.repo.PixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor() : ViewModel() {


    private val _image = MutableStateFlow(imageModelEmpty)
    val image: Flow<ImageModel> get() = _image

    fun setImage(image: ImageModel) {
        _image.value = image
    }
}