package ru.neuromantics.visittobolsk.domain.models

import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.data.models.Phone

data class ParkDomain(
    val id: Int,
    val title: String,
    val address: Address,
    val images: List<Image>,
    val phones: List<Phone>,
)
