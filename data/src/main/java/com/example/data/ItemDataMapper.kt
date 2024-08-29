package com.example.data

import com.example.data.database.ItemEntity
import com.example.domain.ItemDomainModel
import com.example.domain.ItemMapper
import javax.inject.Inject

class ItemDataMapper @Inject constructor(): ItemMapper<ItemDomainModel, ItemEntity> {
    override fun mapToDomain(entity: ItemEntity) = ItemDomainModel(
        entity.id,
        entity.name,
        entity.time,
        entity.tags,
        entity.amount
    )

    override fun mapToEntity(model: ItemDomainModel) = ItemEntity (
        model.id,
        model.name,
        model.time,
        model.tags,
        model.amount
    )
}