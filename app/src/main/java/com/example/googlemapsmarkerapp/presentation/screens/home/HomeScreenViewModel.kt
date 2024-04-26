package com.example.googlemapsmarkerapp.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlemapsmarkerapp.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var state by mutableStateOf(MapState())


    init {
        viewModelScope.launch {
            useCases.getAllLocationUseCase.invoke().collectLatest { markerLocations ->
                state = state.copy(
                    markerLocations = markerLocations
                )
            }
        }
    }


    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties,
                    isFalloutMap = !state.isFalloutMap
                )
            }

            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    useCases.addLocationUseCase.invoke(
                        event.markerLocation
                    )
                }
            }

            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    useCases.deleteLocationUseCase.invoke(event.markerLocation)
                }
            }
        }
    }

}