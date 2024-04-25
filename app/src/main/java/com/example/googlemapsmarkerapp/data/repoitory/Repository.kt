package com.example.googlemapsmarkerapp.data.repoitory

import com.example.googlemapsmarkerapp.domain.repository.LocalDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
) {

}