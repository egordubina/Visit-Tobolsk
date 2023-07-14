package ru.travel.visittobolsk.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.travel.visittobolsk.domain.models.toUi
import ru.travel.visittobolsk.domain.usecases.LoadInterestsScreenUseCaseImpl
import ru.travel.visittobolsk.domain.usecases.SearchUseCaseImpl
import ru.travel.visittobolsk.ui.uistates.MostInterestingUiState

class MostInterestsViewModel(
    private val loadScreenUseCase: LoadInterestsScreenUseCaseImpl,
    private val searchUseCase: SearchUseCaseImpl
) : ViewModel() {
    private var _uiState: MutableStateFlow<MostInterestingUiState> =
        MutableStateFlow(MostInterestingUiState.Loading)
    val uiState: StateFlow<MostInterestingUiState> = _uiState.asStateFlow()
    private var _searchResult: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val searchResult: StateFlow<List<String>> = _searchResult.asStateFlow()
    private var job: Job? = null

    init {
        job?.cancel()
        _uiState.value = MostInterestingUiState.Loading
        job = viewModelScope.launch {
            try {
                val cafes = async {
                    try {
                        loadScreenUseCase.loadAllCafes()
                    } catch (e: Exception) {
                        emptyList()
                    }
                }
                val parks = async {
                    try {
                        loadScreenUseCase.loadAllParks()
                    } catch (e: Exception) {
                        emptyList()
                    }
                }
                val museums = async {
                    try {
                        loadScreenUseCase.loadAllMuseums()
                    } catch (e: Exception) {
                        emptyList()
                    }
                }
                val hotels = async {
                    try {
                        loadScreenUseCase.loadAllHotels()
                    } catch (e: Exception) {
                        emptyList()
                    }
                }
                _uiState.value = MostInterestingUiState.Content(
                    cafesList = cafes.await().toUi(),
                    museumsList = museums.await().toUi(),
                    parksList = parks.await(),
                    hotelsList = hotels.await(),
                )
            } catch (e: Exception) {
                _uiState.value = MostInterestingUiState.Error
            }
        }
    }

    fun search(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            _searchResult.value = searchUseCase.load(query = query)
        }
    }
}