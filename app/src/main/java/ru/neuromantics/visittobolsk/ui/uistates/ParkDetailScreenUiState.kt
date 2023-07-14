package ru.neuromantics.visittobolsk.ui.uistates

import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.data.models.Phone

sealed class ParkDetailScreenUiState {
    object Error : ParkDetailScreenUiState()
    object Loading : ParkDetailScreenUiState()
    data class Content(
        val title: String,
        val address: Address,
        val images: List<Image>,
        val phones: List<Phone>,
    ) : ParkDetailScreenUiState()
}
