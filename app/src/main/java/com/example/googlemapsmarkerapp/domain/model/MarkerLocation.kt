package com.example.googlemapsmarkerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.googlemapsmarkerapp.utils.Constants.MARKER_LOCATION_DATABASE_TABLE

@Entity(tableName = MARKER_LOCATION_DATABASE_TABLE)
data class MarkerLocation(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name:String,
    val relation:String,
    val age:Int,
    val address:String?,
    val  latitude:Double,
    val  longitude:Double,
)
