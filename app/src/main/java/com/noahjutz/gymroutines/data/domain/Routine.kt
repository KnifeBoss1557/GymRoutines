

package com.noahjutz.gymroutines.data.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "routine_table")
data class Routine(
    val name: String = "",
    val hidden: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    var routineId: Int = 0,
)

data class RoutineWithSetGroups(
    @Embedded val routine: Routine,
    @Relation(
        entity = RoutineSetGroup::class,
        parentColumn = "routineId",
        entityColumn = "routineId"
    ) val setGroups: List<RoutineSetGroupWithSets>
)
