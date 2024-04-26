package com.example.googlemapsmarkerapp.presentation.screens.home

import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    data object ToggleFalloutMap: MapEvent()
    data class OnMapLongClick(val markerLocation: MarkerLocation): MapEvent()
    data class OnInfoWindowLongClick(val markerLocation: MarkerLocation): MapEvent()
}