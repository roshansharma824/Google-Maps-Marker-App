package com.example.googlemapsmarkerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.googlemapsmarkerapp.data.local.dao.MarkerLocationDao
import com.example.googlemapsmarkerapp.domain.model.MarkerLocation

@Database(entities = [MarkerLocation::class], version = 1)
abstract class MarkerLocationDatabase : RoomDatabase() {

    abstract fun markerLocationDao(): MarkerLocationDao

}