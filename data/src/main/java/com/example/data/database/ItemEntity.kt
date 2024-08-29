package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.database.converters.TagsConverter

@Entity(tableName = "item")
data class ItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val time: Long,
    @TypeConverters(TagsConverter::class)
    val tags: List<String>,
    val amount: Int
)