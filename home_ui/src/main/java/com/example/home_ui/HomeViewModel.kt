package com.example.home_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.domain.models.ImageModel
import com.example.domain.repo.PixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.SavedStateHandle
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.first

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: PixRepository
) : ViewModel() {

    private val _results = MutableStateFlow<PagingData<ImageModel>?>(null)
    val results: Flow<PagingData<ImageModel>> get() = _results.filterNotNull()

    private val _loading = MutableStateFlow(false)
    val loading: Flow<Boolean> get() = _loading

    private var queryString: String = savedStateHandle["keyword"] ?: "fruits"

    init {
        refreshData()
    }

    private fun refreshData() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _results.value =
                    repo.getSearchResultStream(queryString).cachedIn(viewModelScope).first()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}
