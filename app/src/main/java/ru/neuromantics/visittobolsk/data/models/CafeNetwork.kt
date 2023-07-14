package ru.neuromantics.visittobolsk.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.neuromantics.visittobolsk.domain.models.CafeDomain

@Serializable
data class CafeNetwork(
    @SerialName("id") val id: Int,
    @SerialName("name") val title: String,
    @SerialName("description") val description: String = "",
    @SerialName("price") val avgPrice: Int,
    @SerialName("site") val site: String = "",
    @SerialName("category") val category: Category,
    @SerialName("address") val addresses: List<Address>,
    @SerialName("images") val images: List<Image>,
    @SerialName("phones") val phones: List<Phone> = emptyList(),
    @SerialName("schedule") val schedule: List<String>,
)

fun List<CafeNetwork>.toDomain(): List<CafeDomain> {
    return map {
        CafeDomain(
            id = it.id,
            title = it.title,
            description = it.description,
            avgPrice = it.avgPrice,
            site = it.site,
            category = it.category,
            address = it.addresses,
            images = it.images,
            phones = it.phones,
            schedule = it.schedule,
        )
    }
}

fun CafeNetwork.toDomain(): CafeDomain {
    return CafeDomain(
        id = this.id,
        title = this.title,
        description = this.description,
        avgPrice = this.avgPrice,
        site = this.site,
        category = this.category,
        address = this.addresses,
        images = this.images,
        phones = this.phones,
        schedule = this.schedule,
    )
}
