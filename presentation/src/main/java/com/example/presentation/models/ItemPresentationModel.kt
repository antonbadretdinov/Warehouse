package com.example.presentation.models

data class ItemPresentationModel(
    val id: Int,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
)