package com.example.googlemapsmarkerapp.data.repoitory

import com.example.googlemapsmarkerapp.data.local.MarkerLocationDatabase
import com.example.googlemapsmarkerapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    markerLocationDatabase: MarkerLocationDatabase
) : LocalDataSource {

    private val markerLocationDao = markerLocationDatabase.markerLocationDao()
}