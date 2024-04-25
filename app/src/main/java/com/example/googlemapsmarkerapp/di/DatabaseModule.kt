package com.example.googlemapsmarkerapp.di

import android.content.Context
import androidx.room.Room
import com.example.googlemapsmarkerapp.data.local.MarkerLocationDatabase
import com.example.googlemapsmarkerapp.data.repoitory.LocalDataSourceImpl
import com.example.googlemapsmarkerapp.domain.repository.LocalDataSource
import com.example.googlemapsmarkerapp.utils.Constants.MARKER_LOCATION_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MarkerLocationDatabase {
        return Room.databaseBuilder(
            context,
            MarkerLocationDatabase::class.java,
            MARKER_LOCATION_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: MarkerLocationDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(database)
    }

}