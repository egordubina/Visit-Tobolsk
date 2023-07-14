package ru.travel.visittobolsk.ui.uistates

import ru.travel.visittobolsk.data.models.ArModel

sealed class ArUiState {
    object Loading : ArUiState()
    object Error : ArUiState()
    data class Content(
        val arModels: List<ArModel>
    ) : ArUiState()
}