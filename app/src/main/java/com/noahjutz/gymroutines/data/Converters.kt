

@file:Suppress("IllegalIdentifier")

package com.noahjutz.gymroutines.data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromDate(date: Date): Long = date.time

    @TypeConverter
    fun toDate(time: Long): Date = Date(time)
}
