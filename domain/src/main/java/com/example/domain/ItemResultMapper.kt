package com.example.domain



interface ItemMapper<M, ME> {
    fun mapToDomain(entity: ME): M
    fun mapToEntity(model: M): ME
}

data class ItemDomainModel(
    val id: Int,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
)