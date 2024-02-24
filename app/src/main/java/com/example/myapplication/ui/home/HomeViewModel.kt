package com.example.myapplication.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.ImageModel
import com.example.myapplication.domain.models.Result
import com.example.myapplication.domain.repo.PixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: PixRepository
) : ViewModel() {

    private val _results = MutableStateFlow(listOf<ImageModel>())
    val results: Flow<List<ImageModel>> get() = _results

    fun makeSearch(keyword: String) {
        viewModelScope.launch {
            try {
                repo.getSearchResults(keyword).collect { result ->
                    when (result) {
                        is Result.OnSuccess -> {
                            _results.value = result.data
                        }
                        is Result.OnError -> {
                            _results.value = emptyList()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}