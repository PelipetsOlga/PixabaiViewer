package com.example.myapplication.ui.photo

import androidx.lifecycle.ViewModel
import com.example.domain.repo.PixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repo: PixRepository
) : ViewModel() {

}