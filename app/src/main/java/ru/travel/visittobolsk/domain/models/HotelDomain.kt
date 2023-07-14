package ru.travel.visittobolsk.domain.models

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone

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
