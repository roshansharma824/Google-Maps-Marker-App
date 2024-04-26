package com.example.googlemapsmarkerapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkerLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarkerLocation(markerLocation: MarkerLocation)

    @Delete
    suspend fun deleteMarkerLocation(markerLocation: MarkerLocation)

    @Query("SELECT * FROM marker_location_table")
    fun getMarkerLocations(): Flow<List<MarkerLocation>>
}