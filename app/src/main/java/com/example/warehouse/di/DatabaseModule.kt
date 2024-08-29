package com.example.warehouse.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.ItemsDatabase
import com.example.warehouse.helpers.DATABASE_FILE_NAME
import com.example.warehouse.helpers.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideItemsDatabase(
        @ApplicationContext appContext: Context
    ) = Room.databaseBuilder(
        context = appContext,
        klass = ItemsDatabase::class.java,
        name = DATABASE_NAME
    )
        .createFromAsset(databaseFilePath = DATABASE_FILE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideItemsDAO(database: ItemsDatabase) = database.itemsDAO()
}