package ru.travel.visittobolsk.ui.models

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Category
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone

data class CafeUi(
    val id: Int,
    val title: String,
    val description: String,
    val avgPrice: Int,
    val site: String,
    val category: Category,
    val addresses: List<Address>,
    val images: List<Image>,
    val phones: List<Phone>,
    val schedule: List<String>,
    val isOpen: Boolean,
    val time: String,
)
