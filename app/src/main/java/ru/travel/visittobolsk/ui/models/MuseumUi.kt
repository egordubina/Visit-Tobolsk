package ru.travel.visittobolsk.ui.models

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone

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
