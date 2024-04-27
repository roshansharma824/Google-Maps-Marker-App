package com.example.googlemapsmarkerapp.presentation.screens.saved

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import com.example.googlemapsmarkerapp.domain.usecase.UseCases
import com.example.googlemapsmarkerapp.presentation.screens.home.MapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedScreenViewModel @Inject constructor(
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

    fun onDeleteLocation(markerLocation: MarkerLocation){
        viewModelScope.launch {
            useCases.deleteLocationUseCase.invoke(markerLocation)
        }
    }
}