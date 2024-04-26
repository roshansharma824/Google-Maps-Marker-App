package com.example.googlemapsmarkerapp.presentation.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import com.example.googlemapsmarkerapp.presentation.component.ProgressIndicator
import com.example.googlemapsmarkerapp.utils.getCurrentLocation
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import java.util.Locale

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var location by remember { mutableStateOf(LatLng(28.524521584703603, 77.18551374973292)) }
    var showMap by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = Unit) {
        getCurrentLocation(context) {
            location = it
            showMap = true
        }
    }

    if (showMap ){
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(location, 15f)
        }
        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                uiSettings = MapUiSettings(),
                cameraPositionState = cameraPositionState,
                onMapLongClick = {

                },
                onMapClick = {
                    onSaveLocation(it, homeScreenViewModel,context)
                }
            ) {

                homeScreenViewModel.state.markerLocations.forEach { markerLocation ->
                    Marker(
                        state = rememberMarkerState(
                            position = LatLng(
                                markerLocation.latitude,
                                markerLocation.longitude
                            ),
                        ),
                        title = "Lat: ${markerLocation.latitude}\nLon: ${markerLocation.latitude}",
                        snippet = "Long click to delete \n Long click to delete",
                        onInfoWindowLongClick = {
                            homeScreenViewModel.onEvent(
                                MapEvent.OnInfoWindowLongClick(markerLocation)
                            )
                        },
                        onClick = {
                            it.showInfoWindow()
                            true
                        },
                        icon = BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_RED
                        )
                    )
                }

            }

            Button(onClick = {
                getCurrentLocation(context) {
                    location = it
                    showMap = true
                }
            }) {
                Text(text = "Location")
            }
        }
    }
    else{
        ProgressIndicator()
    }

}

@SuppressLint("MissingPermission")
fun onSaveLocation(location: LatLng, homeScreenViewModel: HomeScreenViewModel, context: Context) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

    if (addresses != null) {
        if (addresses.isNotEmpty()) {
            val address = addresses[0]
            val addressLine = address?.getAddressLine(0)
            val markerLocation = MarkerLocation(
                name = "",
                relation = "",
                age = 22,
                address = addressLine,
                latitude = location.latitude,
                longitude = location.longitude
            )

            homeScreenViewModel.onEvent(MapEvent.OnMapLongClick(markerLocation))
            // Now you possess the human-readable address in 'addressLine'
        }
    }


}