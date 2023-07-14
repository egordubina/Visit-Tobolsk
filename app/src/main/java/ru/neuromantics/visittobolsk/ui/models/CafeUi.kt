package ru.neuromantics.visittobolsk.ui.models

import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.Category
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.data.models.Phone

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
