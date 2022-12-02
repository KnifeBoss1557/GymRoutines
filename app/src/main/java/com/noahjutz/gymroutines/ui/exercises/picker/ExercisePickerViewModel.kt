
package com.noahjutz.gymroutines.ui.exercises.picker

import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.ExerciseRepository
import com.noahjutz.gymroutines.data.domain.Exercise
import java.util.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class ExercisePickerViewModel(
    private val exerciseRepository: ExerciseRepository,
) : ViewModel() {
    private val _nameFilter = MutableStateFlow("")
    private val exercises = exerciseRepository.exercises
    private val _selectedExercises = MutableStateFlow(emptyList<Exercise>())

    fun search(name: String) {
        _nameFilter.value = name
    }

    fun addExercise(exercise: Exercise) {
        _selectedExercises.value =
            _selectedExercises.value.toMutableList().apply { add(exercise) }
    }

    fun removeExercise(exercise: Exercise) {
        _selectedExercises.value =
            _selectedExercises.value.toMutableList().apply { remove(exercise) }
    }

    fun clearExercises() {
        _selectedExercises.value = emptyList()
    }

    val nameFilter = _nameFilter.asStateFlow()

    val allExercises = exercises.combine(_nameFilter) { exercises, nameFilter ->
        exercises.filter {
            it.name.lowercase(Locale.getDefault())
                .contains(nameFilter.lowercase(Locale.getDefault()))
        }
    }

    val selectedExercises = _selectedExercises.asStateFlow()

    val selectedExerciseIds = selectedExercises.map { it.map { it.exerciseId } }

    fun exercisesContains(exercise: Exercise) = selectedExercises.map { it.contains(exercise) }
}
