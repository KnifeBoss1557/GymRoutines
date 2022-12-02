

package com.noahjutz.gymroutines.data.dao

import androidx.room.*
import com.noahjutz.gymroutines.data.domain.Workout
import com.noahjutz.gymroutines.data.domain.WorkoutSet
import com.noahjutz.gymroutines.data.domain.WorkoutSetGroup
import com.noahjutz.gymroutines.data.domain.WorkoutWithSetGroups
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(workout: Workout): Long

    @Update
    suspend fun update(workout: Workout)

    @Insert
    suspend fun insert(workoutSet: WorkoutSet): Long

    @Update
    suspend fun update(workoutSet: WorkoutSet)

    @Insert
    suspend fun insert(workoutSetGroup: WorkoutSetGroup): Long

    @Update
    suspend fun update(workoutSetGroup: WorkoutSetGroup)

    @Transaction
    @Query("SELECT * FROM workout_table WHERE workoutId == :id")
    suspend fun getWorkoutWithSetGroups(id: Int): WorkoutWithSetGroups?

    @Query("SELECT * FROM workout_table WHERE workoutId == :id")
    suspend fun getWorkout(id: Int): Workout?

    @Query("SELECT * FROM workout_set_group_table WHERE workoutId == :workoutId")
    suspend fun getSetGroupsInWorkout(workoutId: Int): List<WorkoutSetGroup>

    @Query("SELECT * FROM workout_set_table WHERE groupId in (SELECT groupId FROM workout_set_group_table WHERE workoutId == :workoutId)")
    suspend fun getSetsInWorkout(workoutId: Int): List<WorkoutSet>

    @Transaction
    @Query("SELECT * FROM workout_table WHERE workoutId == :id")
    fun getWorkoutWithSetGroupsFlow(id: Int): Flow<WorkoutWithSetGroups?>

    @Transaction
    @Query("SELECT * FROM workout_table ORDER BY startTime DESC")
    fun getWorkouts(): Flow<List<Workout>>

    @Delete
    suspend fun delete(workout: Workout)

    @Delete
    suspend fun delete(workoutSet: WorkoutSet)

    @Delete
    suspend fun delete(workoutSetGroup: WorkoutSetGroup)

    @Query("SELECT * FROM workout_set_group_table WHERE id == :id")
    suspend fun getSetGroup(id: Int): WorkoutSetGroup?

    @Query("SELECT * FROM workout_set_group_table WHERE workoutId == :workoutId")
    suspend fun getSetGroups(workoutId: Int): List<WorkoutSetGroup>

    @Query("SELECT * FROM workout_set_table WHERE groupId == :groupId")
    suspend fun getSetsInGroup(groupId: Int): List<WorkoutSet>
}
