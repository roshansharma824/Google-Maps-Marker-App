package com.example.googlemapsmarkerapp.data.repoitory

import com.example.googlemapsmarkerapp.data.local.MarkerLocationDatabase
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import com.example.googlemapsmarkerapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    markerLocationDatabase: MarkerLocationDatabase
) : LocalDataSource {

    private val markerLocationDao = markerLocationDatabase.markerLocationDao()
    override suspend fun insertMarkerLocation(markerLocation: MarkerLocation) {
        markerLocationDao.insertMarkerLocation(markerLocation)
    }

    override suspend fun deleteMarkerLocation(markerLocation: MarkerLocation) {
        markerLocationDao.deleteMarkerLocation(markerLocation)
    }

    override fun getMarkerLocations(): Flow<List<MarkerLocation>> {
        return  markerLocationDao.getMarkerLocations()
    }
}