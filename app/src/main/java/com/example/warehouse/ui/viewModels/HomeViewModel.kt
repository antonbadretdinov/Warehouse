package com.example.warehouse.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.data.database.models.ItemModel
import com.example.warehouse.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }

    val itemsStateFlow = homeRepository.getAllItems().stateIn(
        scope = viewModelScope + Dispatchers.IO + coroutineExceptionHandler,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun updateItem(itemModel: ItemModel) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            homeRepository.updateItem(itemModel)
        }
    }

    fun deleteItem(itemModel: ItemModel) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            homeRepository.deleteItem(itemModel)
        }
    }

}