package com.example.googlemapsmarkerapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.example.googlemapsmarkerapp.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

}