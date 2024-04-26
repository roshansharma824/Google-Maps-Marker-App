package com.example.googlemapsmarkerapp.domain.usecase.getalllocationusecase

import com.example.googlemapsmarkerapp.data.repoitory.Repository
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation

class GetAllLocationUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getMarkerLocations()
}