package com.example.moviesappselect.di

import com.example.domain.repository.Repository
import com.example.domain.usecases.FetchUseCase
import com.example.moviesappselect.model.MapDomainToApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideFetchUseCase(repository: Repository): FetchUseCase =
        FetchUseCase.Base(repository = repository)

    @Provides
    fun provideMapDomainToApp(): MapDomainToApp =
        MapDomainToApp.Base()
}