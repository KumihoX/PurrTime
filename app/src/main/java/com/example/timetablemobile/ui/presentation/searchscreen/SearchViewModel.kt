package com.example.timetablemobile.ui.presentation.searchscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetablemobile.R
import com.example.timetablemobile.common.Constants
import com.example.timetablemobile.domain.usecase.list.GetCabinetsListUseCase
import com.example.timetablemobile.domain.usecase.list.GetGroupsListUseCase
import com.example.timetablemobile.domain.usecase.list.GetTeachersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCabinetsUseCase: GetCabinetsListUseCase,
    private val getGroupsUseCase: GetGroupsListUseCase,
    private val getTeachersUseCase: GetTeachersListUseCase
) : ViewModel() {

    private val _state: MutableState<SearchState> = mutableStateOf(SearchState.Initial)
    var state: State<SearchState> = _state

    private val _requestResult = mutableStateOf<List<Any>>(emptyList())
    private val _searchResult = mutableStateOf<List<Any>>(emptyList())
    var searchResult: State<List<Any>> = _searchResult

    private val _searchFieldText = mutableStateOf("")
    var searchFieldText: State<String> = _searchFieldText

    /*init {
        _state.value = SearchState.Initial
    }*/

    fun onSearchFieldChange(newValue: String) {
        _searchFieldText.value = newValue

        if (newValue.isNotEmpty())
            getSearchResult(newValue, _requestResult.value)
        else
            _searchResult.value = _requestResult.value
    }

    fun getList(choice: String) {
        when(choice) {
            "Аудитории" -> getCabinets()
            "Группы" -> getGroups()
            "Преподаватели" -> getTeachers()
            else -> _state.value = SearchState.Error("Что-то пошло не так")
        }
    }

    private fun getSearchResult(value: String, list: List<Any>) {

        val sortedList = ArrayList<Any>()

        for (item in list) {
            if (item.toString()
                    .lowercase(Locale.getDefault())
                    .contains(value.lowercase(Locale.getDefault()))
            ) {
                sortedList.add(item)
            }
        }

        _searchResult.value = sortedList
    }

    private fun getCabinets() {
        viewModelScope.launch {
            _state.value = SearchState.Loading

            try {

                val cabinets = getCabinetsUseCase()
                _state.value = SearchState.Content(cabinets.cabinets)

                _requestResult.value = cabinets.cabinets
                _searchResult.value = cabinets.cabinets

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

                _requestResult.value = groups.groups
                _searchResult.value = groups.groups

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
                _state.value = SearchState.Content(teachers.teachers)

                _requestResult.value = teachers.teachers.map { it.name }
                _searchResult.value = teachers.teachers.map { it.name }

            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }
}