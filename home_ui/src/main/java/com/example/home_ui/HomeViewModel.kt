package com.example.home_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.ImageModel
import com.example.domain.repo.PixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: PixRepository
) : ViewModel() {

    private val _results = MutableStateFlow<PagingData<ImageModel>?>(null)
    val results: Flow<PagingData<ImageModel>> get() = _results.filterNotNull()

    private val _loading = MutableStateFlow(false)
    val loading: Flow<Boolean> get() = _loading

    private val _searchText = MutableStateFlow("fruits")
    val searchText = _searchText.asStateFlow()

    init {
        refreshData()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        _results.value = null
        if (text.length > 2) {
            refreshData()
        }
    }

    private fun refreshData() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _results.value =
                    repo.getSearchResultStream(_searchText.value).cachedIn(viewModelScope).first()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}
