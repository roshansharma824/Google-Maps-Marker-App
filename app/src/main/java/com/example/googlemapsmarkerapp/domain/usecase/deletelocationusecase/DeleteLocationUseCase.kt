package com.example.googlemapsmarkerapp.domain.usecase.deletelocationusecase

import com.example.googlemapsmarkerapp.data.repoitory.Repository
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation

class DeleteLocationUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(markerLocation: MarkerLocation) = repository.deleteMarkerLocation(markerLocation)
}