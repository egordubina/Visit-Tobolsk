package ru.travel.visittobolsk.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.filament.Engine
import io.github.sceneview.ar.node.ArNode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.travel.visittobolsk.data.models.ArModel
import ru.travel.visittobolsk.domain.usecases.LoadArUseCase
import ru.travel.visittobolsk.ui.uistates.ArUiState

class ArViewModel(private val loadArUseCase: LoadArUseCase) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    private val _isError = MutableStateFlow(false)
    private val _models = MutableStateFlow(emptyList<ArModel>())
    val uiState = combine(_isLoading, _isError, _models) { loading, error, models ->
        ArUiState.Content(arModels = models)
    }.stateIn(
        initialValue = ArUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
        scope = viewModelScope
    )

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _models.value = loadArUseCase.loadModels()
                _isError.value = false
            } catch (e: Exception) {
                _isError.value = true
            }
            _isLoading.value = false
        }
    }
}