package com.example.warehouse.di

import com.example.data.ItemsRepositoryImpl
import com.example.domain.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideRepository(impl: ItemsRepositoryImpl): ItemsRepository
}