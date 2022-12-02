

package com.noahjutz.gymroutines.ui.workout.insights

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noahjutz.gymroutines.data.AppPrefs
import com.noahjutz.gymroutines.data.RoutineRepository
import com.noahjutz.gymroutines.data.WorkoutRepository
import com.noahjutz.gymroutines.data.domain.Workout
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WorkoutInsightsViewModel(
    private val workoutRepository: WorkoutRepository,
    private val routineRepository: RoutineRepository,
    private val preferences: DataStore<Preferences>,
) : ViewModel() {
    val workouts = workoutRepository.workouts.combine(preferences.data) { workouts, prefs ->
        workouts.filter {
            prefs[AppPrefs.CurrentWorkout.key] != it.workoutId
        }
    }
    val routineNames = workouts.map { workouts ->
        workouts.associate {
            Pair(it.workoutId, getRoutineName(it.routineId))
        }
    }

    fun delete(workout: Workout) = viewModelScope.launch {
        workoutRepository.delete(workout)
    }

    suspend fun getRoutineName(routineId: Int): String {
        val routine = routineRepository.getRoutine(routineId)
        return routine?.name ?: ""
    }
}
