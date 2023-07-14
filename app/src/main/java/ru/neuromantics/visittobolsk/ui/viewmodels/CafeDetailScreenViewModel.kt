package ru.neuromantics.visittobolsk.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.neuromantics.visittobolsk.domain.usecases.LoadDetailCafeUseCaseImpl
import ru.neuromantics.visittobolsk.ui.uistates.CafeDetailScreenUiState

class CafeDetailScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val loadDetailCafeUseCase: LoadDetailCafeUseCaseImpl
) : ViewModel() {
    private val _uiState: MutableStateFlow<CafeDetailScreenUiState> =
        MutableStateFlow(CafeDetailScreenUiState.Loading)
    val uiState: StateFlow<CafeDetailScreenUiState> = _uiState.asStateFlow()
    private var job: Job? = null

    init {
        job?.cancel()
        _uiState.value = CafeDetailScreenUiState.Loading
        job = viewModelScope.launch(Dispatchers.IO) {
            val id: Int = checkNotNull(savedStateHandle["cafeId"])
            try {
                val response = loadDetailCafeUseCase.loadCafeById(id = id)
                _uiState.value = CafeDetailScreenUiState.Content(
                    name = response.title,
                    description = response.description,
                    address = response.address,
                    phones = response.phones,
                    images = response.images,
                    site = response.site,
                    category = response.category,
                    price = response.avgPrice,
                    schedule = response.schedule,
                    isOpen = response.isOpen,
                    time = response.time
                )
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
                _uiState.value = CafeDetailScreenUiState.Error
            }
        }
    }
}
