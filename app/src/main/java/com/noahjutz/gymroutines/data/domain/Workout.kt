

package com.noahjutz.gymroutines.data.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

@Entity(tableName = "workout_table")
data class Workout(
    val routineId: Int,
    val startTime: Date = Calendar.getInstance().time,
    val endTime: Date = startTime,

    @PrimaryKey(autoGenerate = true)
    val workoutId: Int = 0,
)

data class WorkoutWithSetGroups(
    @Embedded val workout: Workout,
    @Relation(
        entity = WorkoutSetGroup::class,
        parentColumn = "workoutId",
        entityColumn = "workoutId"
    ) val setGroups: List<WorkoutSetGroupWithSets>
)

val Workout.duration get() = (endTime.time - startTime.time).milliseconds
