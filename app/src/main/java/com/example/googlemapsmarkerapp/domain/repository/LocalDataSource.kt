package com.example.googlemapsmarkerapp.domain.repository

import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertMarkerLocation(markerLocation: MarkerLocation)
    suspend fun deleteMarkerLocation(markerLocation: MarkerLocation)
    fun getMarkerLocations(): Flow<List<MarkerLocation>>
}