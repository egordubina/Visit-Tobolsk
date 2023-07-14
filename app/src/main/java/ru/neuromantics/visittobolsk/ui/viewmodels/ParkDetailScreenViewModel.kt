package ru.neuromantics.visittobolsk.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.neuromantics.visittobolsk.domain.usecases.LoadDetailParkUseCaseImpl
import ru.neuromantics.visittobolsk.ui.uistates.ParkDetailScreenUiState

class ParkDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
    loadDetailParkUseCase: LoadDetailParkUseCaseImpl
) : ViewModel() {
    private var _uiState =
        MutableStateFlow<ParkDetailScreenUiState>(ParkDetailScreenUiState.Loading)
    val uiState: StateFlow<ParkDetailScreenUiState> = _uiState.asStateFlow()
    private var job: Job? = null

    init {
        job?.cancel()
        _uiState.value = ParkDetailScreenUiState.Loading
        job = viewModelScope.launch {
            val id: Int = checkNotNull(savedStateHandle["parkId"])
            try {
                val response = loadDetailParkUseCase.loadParkById(id = id)
                _uiState.value = ParkDetailScreenUiState.Content(
                    title = response.title,
                    images = response.images,
                    address = response.address,
                    phones = response.phones,
//                    site = response.site,
//                    price = response.price, // TODO: delete price
//                    schedule = response.schedule,
                )
            } catch (e: Exception) {
                _uiState.value = ParkDetailScreenUiState.Error
            }
        }
    }
}