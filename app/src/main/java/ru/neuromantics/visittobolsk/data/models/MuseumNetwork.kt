package ru.neuromantics.visittobolsk.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.neuromantics.visittobolsk.domain.models.MuseumDomain

@Serializable
data class MuseumNetwork(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("image") val images: List<Image>,
    @SerialName("address") val address: Address,
    @SerialName("phone") val phone: List<Phone>,
    @SerialName("site") val site: String,
    @SerialName("price") val price: Int,
    @SerialName("schedule") val schedule: List<String>,
)

fun List<MuseumNetwork>.toDomain(): List<MuseumDomain> {
    return map {
        MuseumDomain(
            id = it.id,
            title = it.title,
            images = it.images,
            address = it.address,
            phone = it.phone,
            site = it.site,
            price = it.price,
            schedule = it.schedule
        )
    }
}

fun MuseumNetwork.toDomain(): MuseumDomain {
    return MuseumDomain(
        id = this.id,
        title = this.title,
        images = this.images,
        address = this.address,
        phone = this.phone,
        site = this.site,
        price = this.price,
        schedule = this.schedule
    )
}
