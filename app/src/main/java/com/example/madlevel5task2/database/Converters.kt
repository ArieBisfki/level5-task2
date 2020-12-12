package com.example.madlevel5task2.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromDate(date: Date?) = date?.time

    @TypeConverter
    fun longToDate(long: Long?) = long?.let { Date(it) }
}