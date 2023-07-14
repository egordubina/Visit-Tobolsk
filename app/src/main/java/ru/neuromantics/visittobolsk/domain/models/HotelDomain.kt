package ru.neuromantics.visittobolsk.domain.models

import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.data.models.Phone

data class HotelDomain(
    val id: Int,
    val title: String,
    val description: String,
    val images: List<Image>,
    val address: Address,
    val phone: List<Phone>,
    val price: Int,
    val site: List<String>,
    val stars: Int,
)
