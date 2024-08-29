package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDAO {

    @Query("SELECT * FROM item")
    fun getAllItems(): Flow<List<ItemEntity>>

    @Update
    suspend fun updateItem(itemEntity: ItemEntity)

    @Delete
    suspend fun deleteItem(itemEntity: ItemEntity)

}