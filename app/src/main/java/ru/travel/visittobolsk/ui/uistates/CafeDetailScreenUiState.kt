package ru.travel.visittobolsk.ui.uistates

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Category
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone

sealed class CafeDetailScreenUiState {
    object Loading : CafeDetailScreenUiState()
    object Error : CafeDetailScreenUiState()
    data class Content(
        val name: String,
        val description: String,
        val address: List<Address>,
        val phones: List<Phone>,
        val images: List<Image>,
        val site: String,
        val category: Category,
        val price: Int,
        val schedule: List<String>,
        val isOpen: Boolean,
        val time: String,
    ) : CafeDetailScreenUiState()
}
