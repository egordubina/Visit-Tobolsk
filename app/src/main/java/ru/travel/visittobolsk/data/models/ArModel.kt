package ru.travel.visittobolsk.data.models

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class ArModel(
    val id: Int,
    val name: String,
    val model: String,
    @DrawableRes val image: Int,
)
