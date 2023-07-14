package ru.neuromantics.visittobolsk.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.neuromantics.visittobolsk.domain.models.HotelDomain

@Serializable
data class HotelNetwork(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("images") val images: List<Image>,
    @SerialName("address") val address: Address,
    @SerialName("phone") val phone: List<Phone>,
    @SerialName("price") val price: Int,
    @SerialName("site") val site: List<String>,
    @SerialName("stars") val stars: Int,
)

fun List<HotelNetwork>.toDomain(): List<HotelDomain> {
    return map {
        HotelDomain(
            id = it.id,
            title = it.title,
            description = it.description,
            address = it.address,
            phone = it.phone,
            price = it.price,
            site = it.site,
            stars = it.stars,
            images = it.images
        )
    }
}

fun HotelNetwork.toDomain(): HotelDomain {
    return HotelDomain(
        id = this.id,
        title = this.title,
        description = this.description,
        address = this.address,
        phone = this.phone,
        price = this.price,
        site = this.site,
        stars = this.stars,
        images = this.images
    )
}