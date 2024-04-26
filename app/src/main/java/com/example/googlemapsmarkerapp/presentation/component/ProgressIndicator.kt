package com.example.googlemapsmarkerapp.presentation.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun ProgressIndicator(){
    Dialog(onDismissRequest = { }) {
        CircularProgressIndicator()
//        Text(text = "Loading")
    }
}