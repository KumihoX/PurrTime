package com.example.timetablemobile.ui.presentation.searchscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetablemobile.R
import com.example.timetablemobile.common.Constants
import com.example.timetablemobile.data.remote.dto.toCabinetsNumbersList
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
    savedStateHandle: SavedStateHandle,
    private val getCabinetsUseCase: GetCabinetsListUseCase,
    private val getGroupsUseCase: GetGroupsListUseCase,
    private val getTeachersUseCase: GetTeachersListUseCase
) : ViewModel() {

    private val _state: MutableState<SearchState> = mutableStateOf(SearchState.Initial)
    var state: State<SearchState> = _state

    private val _requestResult = mutableStateOf<List<Any>>(emptyList())
    var requestResult: State<List<Any>> = _requestResult

    private val _searchResult = mutableStateOf<List<Any>>(emptyList())
    var searchResult: State<List<Any>> = _searchResult

    init {
        /*val testList = listOf(100, 123, 124, 125, 126, 127, 128, 129, 200, 201, 202, 300, 301, 302, 1054)

        _state.value = SearchState.Content(testList)
        _requestResult.value = testList
        _searchResult.value = testList*/

        savedStateHandle.get<String>(Constants.PARAM_UNSIGNED_CHOICE)?.let { choice ->
            when(choice) {
                R.string.cabinet.toString() -> getCabinets()
                R.string.group.toString() -> getGroups()
                R.string.teacher.toString() -> getTeachers()
                else -> _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }

    private val _searchFieldText = mutableStateOf("")
    var searchFieldText: State<String> = _searchFieldText

    fun onSearchFieldChange(newValue: String, requestResultList: List<Any>) {
        _searchFieldText.value = newValue

        if (newValue.isNotEmpty())
            getSearchResult(newValue, requestResultList)
        else
            _searchResult.value = _requestResult.value
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
                _state.value = SearchState.Content(cabinets.toCabinetsNumbersList())
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
                _state.value = SearchState.Content(groups.groupsList)
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
                _state.value = SearchState.Content(teachers.teachersList)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }
}