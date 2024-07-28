package com.example.warehouse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.warehouse.data.database.converters.TagsConverter
import com.example.warehouse.data.database.models.ItemModel

@TypeConverters(TagsConverter::class)
@Database(entities = [ItemModel::class], version = 1)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemsDAO(): ItemsDAO
}