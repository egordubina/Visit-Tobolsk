package ru.neuromantics.visittobolsk.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.neuromantics.visittobolsk.domain.models.toUi
import ru.neuromantics.visittobolsk.domain.usecases.LoadInterestsScreenUseCaseImpl
import ru.neuromantics.visittobolsk.ui.uistates.MostInterestingUiState

class MostInterestsViewModel(
    private val loadScreenUseCase: LoadInterestsScreenUseCaseImpl
) : ViewModel() {
    private var _uiState: MutableStateFlow<MostInterestingUiState> =
        MutableStateFlow(MostInterestingUiState.Loading)
    val uiState: StateFlow<MostInterestingUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = MostInterestingUiState.Loading
        viewModelScope.launch {
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
}