package com.example.timetablemobile.ui.presentation.searchscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetablemobile.data.remote.dto.toCabinetList
import com.example.timetablemobile.data.remote.dto.toTeacherList
import com.example.timetablemobile.domain.model.Cabinet
import com.example.timetablemobile.domain.model.Teacher
import com.example.timetablemobile.domain.usecase.list.GetCabinetsListUseCase
import com.example.timetablemobile.domain.usecase.list.GetGroupsListUseCase
import com.example.timetablemobile.domain.usecase.list.GetTeachersListUseCase
import com.example.timetablemobile.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCabinetsUseCase: GetCabinetsListUseCase,
    private val getGroupsUseCase: GetGroupsListUseCase,
    private val getTeachersUseCase: GetTeachersListUseCase,
) : ViewModel() {

    private val _state: MutableState<SearchState> = mutableStateOf(SearchState.Initial)
    var state: State<SearchState> = _state

    private val _cabinetRequestResult = mutableStateOf<List<Cabinet>>(emptyList())
    private val _cabinetSearchResult = mutableStateOf<List<Cabinet>>(emptyList())
    var cabinetSearchResult: State<List<Cabinet>> = _cabinetSearchResult

    private val _teacherRequestResult = mutableStateOf<List<Teacher>>(emptyList())
    private val _teacherSearchResult = mutableStateOf<List<Teacher>>(emptyList())
    var teacherSearchResult: State<List<Teacher>> = _teacherSearchResult

    private val _groupRequestResult = mutableStateOf<List<Int>>(emptyList())
    private val _groupSearchResult = mutableStateOf<List<Int>>(emptyList())
    var groupSearchResult: State<List<Int>> = _groupSearchResult

    private val _searchFieldText = mutableStateOf("")
    var searchFieldText: State<String> = _searchFieldText

    fun onSearchFieldChange(newValue: String, choice: String) {
        _searchFieldText.value = newValue

        if (newValue.isNotEmpty())
            when(choice) {
                "Аудитории" -> getCabinetSearchResult(newValue, _cabinetRequestResult.value)
                "Группы" -> getGroupSearchResult(newValue, _groupRequestResult.value)
                "Преподаватели" -> getTeacherSearchResult(newValue, _teacherRequestResult.value)
                else -> _state.value = SearchState.Error("Что-то пошло не так")
            }
        else
            when(choice) {
                "Аудитории" -> _cabinetSearchResult.value = _cabinetRequestResult.value
                "Группы" -> _groupSearchResult.value = _groupRequestResult.value
                "Преподаватели" -> _teacherSearchResult.value = _teacherRequestResult.value
            }
    }

    private fun getCabinetSearchResult(value: String, list: List<Cabinet>) {
        val sortedList = ArrayList<Cabinet>()

        for (item in list) {
            if (item.name
                    .lowercase(Locale.getDefault())
                    .contains(value.lowercase(Locale.getDefault()))
            ) {
                sortedList.add(item)
            }
        }

        _cabinetSearchResult.value = sortedList
    }

    private fun getGroupSearchResult(value: String, list: List<Int>) {
        val sortedList = ArrayList<Int>()

        for (item in list) {
            if (item.toString()
                    .lowercase(Locale.getDefault())
                    .contains(value.lowercase(Locale.getDefault()))
            ) {
                sortedList.add(item)
            }
        }

        _groupSearchResult.value = sortedList
    }

    private fun getTeacherSearchResult(value: String, list: List<Teacher>) {
        val sortedList = ArrayList<Teacher>()

        for (item in list) {
            if (item.name
                    .lowercase(Locale.getDefault())
                    .contains(value.lowercase(Locale.getDefault()))
            ) {
                sortedList.add(item)
            }
        }

        _teacherSearchResult.value = sortedList
    }

    fun getList(choice: String) {
        when(choice) {
            "Аудитории" -> getCabinets()
            "Группы" -> getGroups()
            "Преподаватели" -> getTeachers()
            else -> _state.value = SearchState.Error("Что-то пошло не так")
        }
    }

    private fun getCabinets() {
        viewModelScope.launch {
            _state.value = SearchState.Loading

            try {

                val cabinets = getCabinetsUseCase()
                _state.value = SearchState.Content(cabinets.toCabinetList())

                _cabinetRequestResult.value = cabinets.toCabinetList()
                _cabinetSearchResult.value = cabinets.toCabinetList()

            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }

    private fun getGroups() {
        viewModelScope.launch {
            _state.value = SearchState.Loading

            try {

                val groups = getGroupsUseCase()
                _state.value = SearchState.Content(groups.groups)

                _groupRequestResult.value = groups.groups
                _groupSearchResult.value = groups.groups

            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }

    private fun getTeachers() {
        viewModelScope.launch {
            _state.value = SearchState.Loading

            try {

                val teachers = getTeachersUseCase()
                _state.value = SearchState.Content(teachers.toTeacherList())

                _teacherRequestResult.value = teachers.toTeacherList()
                _teacherSearchResult.value = teachers.toTeacherList()

            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }

    fun navigateToCabinetSchedule(navController: NavController, id: Int, name: String) {
        navController.navigate(Screen.MainScreen.passScheduleInfo(
            type = "CABINET",
            dataId = id.toString(),
            data = name
        )) {
            popUpTo(Screen.SearchScreen.route) { inclusive = true }
        }
    }

    fun navigateToTeacherSchedule(navController: NavController, id: String, name: String) {
        navController.navigate(Screen.MainScreen.passScheduleInfo(
            type = "TEACHER",
            dataId = id,
            data = name
        )) {
            popUpTo(Screen.SearchScreen.route) { inclusive = true }
        }
    }

    fun navigateToGroupSchedule(navController: NavController, id: Int) {
        navController.navigate(Screen.MainScreen.passScheduleInfo(
            type = "STUDENT",
            dataId = id.toString()
        )) {
            popUpTo(Screen.SearchScreen.route) { inclusive = true }
        }
    }
}