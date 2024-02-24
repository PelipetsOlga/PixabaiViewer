package com.example.myapplication.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.ImageModel
import com.example.myapplication.domain.repo.PixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.myapplication.domain.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repo: PixRepository
) : ViewModel() {

}