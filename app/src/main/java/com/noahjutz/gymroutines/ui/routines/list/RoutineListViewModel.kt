
package com.noahjutz.gymroutines.ui.routines.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noahjutz.gymroutines.data.RoutineRepository
import com.noahjutz.gymroutines.data.domain.Routine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class RoutineListViewModel(
    private val repository: RoutineRepository,
) : ViewModel() {
    private val _nameFilter = MutableStateFlow("")
    val nameFilter = _nameFilter.asStateFlow()

    val routines: Flow<List<Routine>> =
        repository.routines.combine(nameFilter) { routines, filter ->
            routines.filter { routine ->
                filter.lowercase() in routine.name.lowercase() && !routine.hidden
            }
        }

    fun setNameFilter(name: String) {
        _nameFilter.value = name
    }

    fun deleteRoutine(routineId: Int) {
        viewModelScope.launch {
            repository.getRoutine(routineId)?.let { routine ->
                repository.update(routine.copy(hidden = true))
            }
        }
    }

    fun addRoutine(onComplete: (Long) -> Unit) {
        viewModelScope.launch {
            val id = repository.insert(Routine())
            onComplete(id)
        }
    }
}
