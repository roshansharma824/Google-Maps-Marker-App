package com.example.googlemapsmarkerapp.presentation.screens.home

import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import com.google.maps.android.compose.MapProperties

data class MapState(
    val markerLocations: List<MarkerLocation> = emptyList(),
)