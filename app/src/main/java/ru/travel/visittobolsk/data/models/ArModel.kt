package ru.travel.visittobolsk.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ArModel(
    val id: Int,
    val name: String,
    val model: String,
    val image: String,
)
