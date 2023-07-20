package ru.travel.visittobolsk.ui.uistates

import io.github.sceneview.ar.node.ArNode
import ru.travel.visittobolsk.data.models.ArModel

sealed class ArUiState {
    object Loading : ArUiState()
    object Error : ArUiState()
    data class Content(
        val arModels: List<ArModel>
    ) : ArUiState()
}