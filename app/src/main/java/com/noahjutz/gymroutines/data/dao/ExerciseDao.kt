

package com.noahjutz.gymroutines.data.dao

import androidx.room.*
import com.noahjutz.gymroutines.data.domain.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exercise: Exercise): Long

    @Update
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Query("SELECT * FROM exercise_table ORDER BY name COLLATE NOCASE ASC")
    fun getExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise_table WHERE exerciseId == :id")
    suspend fun getExercise(id: Int): Exercise?

    @Query("SELECT * FROM exercise_table WHERE exerciseId == :id")
    fun getExerciseFlow(id: Int): Flow<Exercise?>
}
