package com.example.domain

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getAllItems(): Flow<List<ItemDomainModel>>

    suspend fun updateItem(itemDomainModel: ItemDomainModel)

    suspend fun deleteItem(itemDomainModel: ItemDomainModel)
}