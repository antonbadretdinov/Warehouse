package com.example.data

import com.example.data.database.ItemsDAO
import com.example.domain.ItemDomainModel
import com.example.domain.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val itemsDAO: ItemsDAO,
    private val itemDataMapper: ItemDataMapper
): ItemsRepository {
    override fun getAllItems(): Flow<List<ItemDomainModel>> {
        return itemsDAO.getAllItems()
            .map { list ->
                list.map { item ->
                    itemDataMapper.mapToDomain(item)
                }
            }
    }

    override suspend fun updateItem(itemDomainModel: ItemDomainModel) {
        itemsDAO.updateItem(itemDataMapper.mapToEntity(itemDomainModel))
    }

    override suspend fun deleteItem(itemDomainModel: ItemDomainModel) {
        itemsDAO.deleteItem(itemDataMapper.mapToEntity(itemDomainModel))
    }
}