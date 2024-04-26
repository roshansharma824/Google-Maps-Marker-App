package com.example.googlemapsmarkerapp.data.repoitory

import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import com.example.googlemapsmarkerapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend fun insertMarkerLocation(markerLocation: MarkerLocation){
        localDataSource.insertMarkerLocation(markerLocation)
    }

    suspend fun deleteMarkerLocation(markerLocation: MarkerLocation){
        localDataSource.deleteMarkerLocation(markerLocation)
    }

    fun getMarkerLocations(): Flow<List<MarkerLocation>> {
        return localDataSource.getMarkerLocations()
    }
}