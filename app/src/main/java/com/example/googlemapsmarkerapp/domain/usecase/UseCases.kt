package com.example.googlemapsmarkerapp.domain.usecase

import com.example.googlemapsmarkerapp.domain.usecase.addLocationusecase.AddLocationUseCase
import com.example.googlemapsmarkerapp.domain.usecase.deletelocationusecase.DeleteLocationUseCase
import com.example.googlemapsmarkerapp.domain.usecase.getalllocationusecase.GetAllLocationUseCase

data class UseCases(
    val addLocationUseCase: AddLocationUseCase,
    val getAllLocationUseCase: GetAllLocationUseCase,
    val deleteLocationUseCase: DeleteLocationUseCase
)
