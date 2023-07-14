package ru.travel.visittobolsk.ui.uistates

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone

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
