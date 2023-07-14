package ru.travel.visittobolsk.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: Int,
    val image: String,
    val name: String,
)