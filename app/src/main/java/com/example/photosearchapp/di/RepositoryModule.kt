package com.example.photosearchapp.di

import com.example.photosearchapp.features.main.data.repository.MainRepositoryImpl
import com.example.photosearchapp.features.main.domain.repository.MainRepository
import com.example.photosearchapp.features.photo_details.data.repository.PhotoDetailsRepositoryImpl
import com.example.photosearchapp.features.photo_details.domain.repository.PhotoDetailsRepository
import com.example.photosearchapp.features.search.data.repository.SearchRepositoryImpl
import com.example.photosearchapp.features.search.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPhotoRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository


    @Binds
    @Singleton
    abstract fun bindDetailsRepository(
        detailsRepositoryImpl: PhotoDetailsRepositoryImpl
    ): PhotoDetailsRepository

}
