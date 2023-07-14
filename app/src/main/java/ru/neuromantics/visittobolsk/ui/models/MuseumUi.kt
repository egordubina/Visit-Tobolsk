package ru.neuromantics.visittobolsk.ui.models

import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.data.models.Phone

data class MuseumUi(
    val id: Int,
    val title: String,
    val images: List<Image>,
    val address: Address,
    val phone: List<Phone>,
    val site: String,
    val price: Int,
    val schedule: List<String>,
    val isOpen: Boolean,
    val time: String,
)
