package ru.travel.visittobolsk.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Phone(
    val id: Int,
    val phone: String,
    val description: String,
)
