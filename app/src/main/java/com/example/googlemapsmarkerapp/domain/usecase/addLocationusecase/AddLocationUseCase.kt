package com.example.googlemapsmarkerapp.domain.usecase.addLocationusecase

import com.example.googlemapsmarkerapp.data.repoitory.Repository
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation

class AddLocationUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(markerLocation: MarkerLocation) = repository.insertMarkerLocation(markerLocation)
}