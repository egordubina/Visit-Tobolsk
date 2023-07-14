package ru.travel.visittobolsk.domain.models

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone

data class ParkDomain(
    val id: Int,
    val title: String,
    val address: Address,
    val images: List<Image>,
    val phones: List<Phone>,
)
