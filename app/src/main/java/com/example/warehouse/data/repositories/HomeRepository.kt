package com.example.warehouse.data.repositories

import com.example.warehouse.data.database.ItemsDAO
import com.example.warehouse.data.database.models.ItemModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val itemsDAO: ItemsDAO) {

    fun getAllItems(): Flow<List<ItemModel>> = itemsDAO.getAllItems()

    suspend fun updateItem(itemModel: ItemModel) = itemsDAO.updateItem(itemModel)

    suspend fun deleteItem(itemModel: ItemModel) = itemsDAO.deleteItem(itemModel)
}