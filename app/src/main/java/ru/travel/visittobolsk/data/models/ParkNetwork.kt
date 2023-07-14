package ru.travel.visittobolsk.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.travel.visittobolsk.domain.models.ParkDomain

@Serializable
data class ParkNetwork(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("address") val address: Address,
    @SerialName("image") val images: List<Image>,
    @SerialName("phones") val phones: List<Phone>,
)

fun List<ParkNetwork>.toDomain(): List<ParkDomain> {
    return map {
        ParkDomain(
            id = it.id,
            title = it.title,
            address = it.address,
            images = it.images,
            phones = it.phones,
        )
    }
}

fun ParkNetwork.toDomain(): ParkDomain {
    return ParkDomain(
        id = this.id,
        title = this.title,
        address = this.address,
        images = this.images,
        phones = this.phones,
    )
}