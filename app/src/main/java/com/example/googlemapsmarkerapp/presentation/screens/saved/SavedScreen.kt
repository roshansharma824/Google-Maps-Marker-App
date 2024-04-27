package com.example.googlemapsmarkerapp.presentation.screens.saved

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import com.example.googlemapsmarkerapp.presentation.theme.Blue
import com.example.googlemapsmarkerapp.presentation.theme.Gray

@Composable
fun SavedScreen(
    savedScreenViewModel: SavedScreenViewModel = hiltViewModel()
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(items = savedScreenViewModel.state.markerLocations, key = { it.id!! }) { item ->
                SavedLocationCard(item, onDelete = {
                    openDialog = true
                })
                DeleteDialog(savedScreenViewModel, item, openDialog) {
                    openDialog = false
                }
            }
        }
    }


}

@Composable
fun SavedLocationCard(item: MarkerLocation, onDelete: () -> Unit) {
    Card(
        elevation = 0.dp, border = BorderStroke(1.dp, Gray.copy(alpha = 0.3f)), modifier = Modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Name:   ${item.name}")
                IconButton(onClick = { onDelete.invoke() }, modifier = Modifier.height(20.dp)) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Gray
                    )
                }
            }

            Text(text = "Age:       ${item.age}")
            Text(text = "Relation:   ${item.relation}")
            Text(text = "Address:   ${item.address}")
            Text(text = "Latitude:   ${item.latitude}")
            Text(text = "Longitude:   ${item.longitude}")
        }
    }


}

@Composable
fun DeleteDialog(
    savedScreenViewModel: SavedScreenViewModel,
    item: MarkerLocation,
    openDialog: Boolean,
    closeDialog: () -> Unit
) {
    if (openDialog) {
        Dialog(onDismissRequest = { closeDialog.invoke() }) {
            Box(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(8))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Are You Sure want to Delete !",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Red
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(text = "Yours marked location will be delete. Are you sure  want to delete ?")

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        OutlinedButton(
                            onClick = { closeDialog.invoke() },
                            border = BorderStroke(1.dp, Gray.copy(alpha = 0.4f)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = "Cancel", color = Blue)
                        }

                        Button(
                            onClick = {
                                savedScreenViewModel.onDeleteLocation(item)
                                closeDialog.invoke()
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Red)
                        ) {
                            Text(text = "Ok", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

