package ru.travel.visittobolsk.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.travel.visittobolsk.domain.models.HotelDomain
import ru.travel.visittobolsk.domain.models.ParkDomain
import ru.travel.visittobolsk.domain.models.toUi
import ru.travel.visittobolsk.domain.usecases.LoadInterestsScreenUseCaseImpl
import ru.travel.visittobolsk.domain.usecases.SearchUseCaseImpl
import ru.travel.visittobolsk.ui.models.CafeUi
import ru.travel.visittobolsk.ui.uistates.MostInterestingUiState

class MostInterestsViewModel(
    private val loadScreenUseCase: LoadInterestsScreenUseCaseImpl,
    private val searchUseCase: SearchUseCaseImpl,
) : ViewModel() {
    private val cafesFlow = MutableStateFlow<List<CafeUi>>(emptyList())
    private val hotelsFlow = MutableStateFlow<List<HotelDomain>>(emptyList())
    private val parksFlow = MutableStateFlow<List<ParkDomain>>(emptyList())
    private var _uiState: MutableStateFlow<MostInterestingUiState> = MutableStateFlow(MostInterestingUiState.Loading)
    val uiState: StateFlow<MostInterestingUiState> = _uiState.asStateFlow()
    private var _searchResult: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val searchResult: StateFlow<List<String>> = _searchResult.asStateFlow()
    private var job: Job? = null

    val newUiState = combine(
        cafesFlow,
        hotelsFlow,
        parksFlow
    ) { cafes, hotels, parks ->
        MostInterestingUiState.Content(
            isLoading = cafes.isEmpty() || hotels.isEmpty() || parks.isEmpty(),
            cafesList = cafes,
            hotelsList = hotels,
            parksList = parks,
            museumsList = emptyList()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = MostInterestingUiState.Loading
    )

    fun search(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            _searchResult.value = searchUseCase.load(query = query)
        }
    }

    init {
        viewModelScope.launch {
            cafesFlow.update { loadScreenUseCase.loadAllCafes().toUi() }
        }
        viewModelScope.launch {
            hotelsFlow.update { loadScreenUseCase.loadAllHotels() }
        }
        viewModelScope.launch {
            parksFlow.update { loadScreenUseCase.loadAllParks() }
        }
    }
}