package ru.travel.visittobolsk.ui.uistates

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone

sealed class HotelDetailScreenUiState {
    object Error : HotelDetailScreenUiState()
    object Loading : HotelDetailScreenUiState()
    data class Content(
        val title: String,
        val description: String,
        val address: Address,
        val images: List<Image>,
        val phones: List<Phone>,
        val sites: List<String>,
        val price: Int,
    ) : HotelDetailScreenUiState()
}
