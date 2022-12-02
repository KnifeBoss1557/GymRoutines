
package com.noahjutz.gymroutines.ui.exercises.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noahjutz.gymroutines.data.ExerciseRepository
import com.noahjutz.gymroutines.data.domain.Exercise
import java.util.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ExerciseListViewModel(
    private val repository: ExerciseRepository,
) : ViewModel() {
    private val _nameFilter = MutableStateFlow("")
    val nameFilter = _nameFilter.asStateFlow()

    fun setNameFilter(filter: String) {
        _nameFilter.value = filter
    }

    val exercises = repository.exercises.combine(_nameFilter) { exercises, nameFilter ->
        exercises.filter {
            it.name.lowercase(Locale.getDefault())
                .contains(nameFilter.lowercase(Locale.getDefault()))
        }
    }

    fun delete(exercise: Exercise) {
        viewModelScope.launch {
            repository.update(exercise.copy(hidden = true))
        }
    }
}
