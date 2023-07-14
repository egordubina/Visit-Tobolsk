package ru.neuromantics.visittobolsk.ui.uistates

import ru.neuromantics.visittobolsk.data.models.ArModel

sealed class ArUiState {
    object Loading : ArUiState()
    object Error : ArUiState()
    data class Content(
        val arModels: List<ArModel>
    ) : ArUiState()
}