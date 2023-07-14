package ru.travel.visittobolsk.ui.uistates

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone

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
