package ru.neuromantics.visittobolsk.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.neuromantics.visittobolsk.domain.usecases.LoadDetailMuseumUseCaseImpl
import ru.neuromantics.visittobolsk.ui.uistates.MuseumDetailScreenUiState

class MuseumDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val loadDetailMuseumUseCase: LoadDetailMuseumUseCaseImpl
) : ViewModel() {
    private var _uiState =
        MutableStateFlow<MuseumDetailScreenUiState>(MuseumDetailScreenUiState.Loading)
    val uiState: StateFlow<MuseumDetailScreenUiState> = _uiState.asStateFlow()
    private var job: Job? = null

    init {
        job?.cancel()
        _uiState.value = MuseumDetailScreenUiState.Loading
        job = viewModelScope.launch {
            val id: Int = checkNotNull(savedStateHandle["museumId"])
            try {
                val response = loadDetailMuseumUseCase.loadMuseumById(id = id)
                _uiState.value = MuseumDetailScreenUiState.Content(
                    title = response.title,
                    images = response.images,
                    address = response.address,
                    phones = response.phone,
                    site = response.site,
                    price = response.price, // TODO: delete price
                    schedule = response.schedule,
                )
            } catch (e: Exception) {
                _uiState.value = MuseumDetailScreenUiState.Error
            }
        }
    }
}
