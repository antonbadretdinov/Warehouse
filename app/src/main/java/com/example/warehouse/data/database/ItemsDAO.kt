package com.example.warehouse.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.warehouse.data.database.models.ItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDAO {

    @Query("SELECT * FROM item")
    fun getAllItems(): Flow<List<ItemModel>>

    @Update
    suspend fun updateItem(itemModel: ItemModel)

    @Delete
    suspend fun deleteItem(itemModel: ItemModel)

}