package com.example.googlemapsmarkerapp.di

import com.example.googlemapsmarkerapp.data.repoitory.Repository
import com.example.googlemapsmarkerapp.domain.usecase.UseCases
import com.example.googlemapsmarkerapp.domain.usecase.addLocationusecase.AddLocationUseCase
import com.example.googlemapsmarkerapp.domain.usecase.deletelocationusecase.DeleteLocationUseCase
import com.example.googlemapsmarkerapp.domain.usecase.getalllocationusecase.GetAllLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UseCases {
        return UseCases(
            addLocationUseCase = AddLocationUseCase(repository),
            getAllLocationUseCase = GetAllLocationUseCase(repository),
            deleteLocationUseCase = DeleteLocationUseCase(repository)
        )
    }

}