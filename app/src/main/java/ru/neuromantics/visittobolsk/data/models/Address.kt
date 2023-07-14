package ru.neuromantics.visittobolsk.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val id: Int,
    @SerialName("address") val addressName: String,
    val location: String,
    val image: String? = null
)
