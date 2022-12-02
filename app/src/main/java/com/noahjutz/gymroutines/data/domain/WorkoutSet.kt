

package com.noahjutz.gymroutines.data.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_set_table",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutSetGroup::class,
            childColumns = ["groupId"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["groupId"])
    ]
)
data class WorkoutSet(
    val groupId: Int,
    val reps: Int? = null,
    val weight: Double? = null,
    val time: Int? = null,
    val distance: Double? = null,
    val complete: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    val workoutSetId: Int = 0
)
