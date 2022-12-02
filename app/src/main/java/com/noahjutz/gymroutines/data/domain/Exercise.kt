
package com.noahjutz.gymroutines.data.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(
    var name: String = "",
    @ColumnInfo(defaultValue = "")
    var notes: String = "",
    var logReps: Boolean = true,
    var logWeight: Boolean = false,
    var logTime: Boolean = false,
    var logDistance: Boolean = false,
    var hidden: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    val exerciseId: Int = 0
)
