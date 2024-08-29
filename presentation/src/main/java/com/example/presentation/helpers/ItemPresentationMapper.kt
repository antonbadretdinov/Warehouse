package com.example.presentation.helpers

import com.example.domain.ItemDomainModel
import com.example.domain.ItemMapper
import com.example.presentation.models.ItemPresentationModel
import javax.inject.Inject

class ItemPresentationMapper @Inject constructor()
    : ItemMapper<ItemDomainModel, ItemPresentationModel> {

    override fun mapToDomain(entity: ItemPresentationModel) = ItemDomainModel(
        entity.id,
        entity.name,
        entity.time,
        entity.tags,
        entity.amount
    )

    override fun mapToEntity(model: ItemDomainModel) = ItemPresentationModel(
        model.id,
        model.name,
        model.time,
        model.tags,
        model.amount
    )
}