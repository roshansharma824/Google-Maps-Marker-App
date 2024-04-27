package com.example.googlemapsmarkerapp.presentation.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import com.example.googlemapsmarkerapp.presentation.component.ProgressIndicator
import com.example.googlemapsmarkerapp.presentation.theme.Gray
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

data class PersonDetail(
    var name: String = "",
    var relation: String = "",
    var age: Int = 0,
    var address: String? = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
)

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var location by remember { mutableStateOf(LatLng(28.524521584703603, 77.18551374973292)) }
    var showMap by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var isError by rememberSaveable { mutableStateOf(false) }
    val (personDetail, setPersonDetails) = remember { mutableStateOf(PersonDetail()) }


    LaunchedEffect(key1 = Unit) {
        getCurrentLocation(context) {
            location = it
            showMap = true
        }
    }

    if (showMap) {
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
                    openDialog = true
                    onSaveLocation(it, context, personDetail, setPersonDetails)
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
                        title = "Name ${markerLocation.name}",
                        snippet = "Address ${markerLocation.address}",
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
        }
    } else {
        ProgressIndicator()
    }


    if (openDialog) {
        Dialog(
            onDismissRequest = {
                openDialog = false
                setPersonDetails(PersonDetail())
            }) {
            Box(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(8))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Enter Person Details",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Gray,
                            unfocusedBorderColor = Gray,
                            unfocusedLabelColor = Gray,
                            focusedLabelColor = Gray,
                            focusedBorderColor = Gray,
                            ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        value = personDetail.name,
                        label = { Text("Name") },
                        onValueChange = {
                            setPersonDetails(personDetail.copy(name = it))
                            if (personDetail.name.isNotEmpty()) isError = false
                        },
                        supportingText = {
                            if (isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Name must fill",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    )
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Gray,
                            unfocusedBorderColor = Gray,
                            unfocusedLabelColor = Gray,
                            focusedLabelColor = Gray,
                            focusedBorderColor = Gray,

                            ),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        value = personDetail.age.toString(),
                        label = { Text("Age") },
                        onValueChange = {
                            setPersonDetails(personDetail.copy(age = it.toInt()))
                        }
                    )
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Gray,
                            unfocusedBorderColor = Gray,
                            unfocusedLabelColor = Gray,
                            focusedLabelColor = Gray,
                            focusedBorderColor = Gray,

                            ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        value = personDetail.relation,
                        label = { Text("Relation") },
                        onValueChange = {
                            setPersonDetails(personDetail.copy(relation = it))
                        }
                    )
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Gray,
                            unfocusedBorderColor = Gray,
                            unfocusedLabelColor = Gray,
                            focusedLabelColor = Gray,
                            focusedBorderColor = Gray,

                            ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        value = "${personDetail.latitude}, ${personDetail.longitude}",
                        label = { Text("Lat, Lon") },
                        onValueChange = {
                        }
                    )
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Gray,
                            unfocusedBorderColor = Gray,
                            unfocusedLabelColor = Gray,
                            focusedLabelColor = Gray,
                            focusedBorderColor = Gray,

                            ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        value = personDetail.address ?: "",
                        label = { Text("Address") },
                        onValueChange = {
                        }
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        onClick = {
                            if (personDetail.name.length <= 2){
                                isError = true
                                return@Button
                            }
                            homeScreenViewModel.onEvent(
                                MapEvent.OnMapLongClick(
                                    MarkerLocation(
                                        name = personDetail.name,
                                        relation = personDetail.relation,
                                        age = personDetail.age,
                                        address = personDetail.address,
                                        latitude = personDetail.latitude,
                                        longitude = personDetail.longitude
                                    )
                                )
                            )
                            openDialog = false
                            setPersonDetails(PersonDetail())
                        }) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }


}

@SuppressLint("MissingPermission")
fun onSaveLocation(
    location: LatLng,
    context: Context,
    personDetail: PersonDetail,
    setPersonDetails: (PersonDetail) -> Unit,
) {
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
            setPersonDetails(
                personDetail.copy(
                    address = addressLine,
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            )

//            homeScreenViewModel.onEvent(MapEvent.OnMapLongClick(markerLocation))
            // Now you possess the human-readable address in 'addressLine'
        }
    }


}