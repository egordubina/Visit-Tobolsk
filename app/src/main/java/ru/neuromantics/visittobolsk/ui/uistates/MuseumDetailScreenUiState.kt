package ru.neuromantics.visittobolsk.ui.uistates

import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.data.models.Phone

sealed class MuseumDetailScreenUiState {
    object Error : MuseumDetailScreenUiState()
    object Loading : MuseumDetailScreenUiState()
    data class Content(
        val title: String,
        val images: List<Image>,
        val address: Address,
        val phones: List<Phone>,
        val site: String,
        val price: Int,
        val schedule: List<String>,
    ) : MuseumDetailScreenUiState()
}
