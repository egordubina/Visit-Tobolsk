package ru.travel.visittobolsk.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.travel.visittobolsk.domain.usecases.LoadDetailHotelUseCaseImpl
import ru.travel.visittobolsk.ui.uistates.HotelDetailScreenUiState

class HotelDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
    loadDetailHotelUseCase: LoadDetailHotelUseCaseImpl
) : ViewModel() {
    private var _uiState =
        MutableStateFlow<HotelDetailScreenUiState>(HotelDetailScreenUiState.Loading)
    val uiState: StateFlow<HotelDetailScreenUiState> = _uiState.asStateFlow()
    private var job: Job? = null

    init {
        job?.cancel()
        _uiState.value = HotelDetailScreenUiState.Loading
        job = viewModelScope.launch {
            val id: Int = checkNotNull(savedStateHandle["hotelId"])
            try {
                val response = loadDetailHotelUseCase.loadHotelById(id = id)
                _uiState.value = HotelDetailScreenUiState.Content(
                    title = response.title,
                    description = response.description,
                    images = response.images,
                    address = response.address,
                    phones = response.phone,
                    sites = response.site,
                    price = response.price
                )
            } catch (e: Exception) {
                _uiState.value = HotelDetailScreenUiState.Error
            }
        }
    }
}