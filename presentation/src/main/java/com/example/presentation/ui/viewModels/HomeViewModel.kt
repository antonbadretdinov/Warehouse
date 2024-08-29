package com.example.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ItemsRepository
import com.example.presentation.helpers.ItemPresentationMapper
import com.example.presentation.models.ItemPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: ItemsRepository,
    private val mapper: ItemPresentationMapper
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }

    private val mutableItemsStateFlow = MutableStateFlow(emptyList<ItemPresentationModel>())

    val itemsStateFlow: StateFlow<List<ItemPresentationModel>> = mutableItemsStateFlow.asStateFlow()

    private val scope = viewModelScope + Dispatchers.IO + coroutineExceptionHandler

    private val sourceStateFlow = homeRepository.getAllItems().stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        scope.launch {
            sourceStateFlow.collectLatest { itemList ->
                mutableItemsStateFlow.value = itemList
                    .map { itemModel ->
                        mapper.mapToEntity(itemModel)
                    }
            }
        }
    }

    fun updateItem(itemPresentationModel: ItemPresentationModel) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            homeRepository.updateItem(
                mapper.mapToDomain(itemPresentationModel)
            )
        }
    }

    fun deleteItem(itemPresentationModel: ItemPresentationModel) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            homeRepository.deleteItem(
                mapper.mapToDomain(itemPresentationModel)
            )
        }
    }
}