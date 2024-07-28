package com.example.warehouse.data.database.models

import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.warehouse.data.database.converters.TagsConverter

@Stable
@Entity(tableName = "item")
data class ItemModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val time: Long,
    @TypeConverters(TagsConverter::class)
    val tags: List<String>,
    val amount: Int
)