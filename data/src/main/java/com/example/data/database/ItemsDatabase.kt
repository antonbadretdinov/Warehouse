package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.converters.TagsConverter

@TypeConverters(TagsConverter::class)
@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemsDAO(): ItemsDAO
}