package ru.neuromantics.visittobolsk.domain.models

import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.Category
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.data.models.Phone
import ru.neuromantics.visittobolsk.domain.DateTimeUtil
import ru.neuromantics.visittobolsk.ui.models.CafeUi

data class CafeDomain(
    val id: Int,
    val title: String,
    val description: String,
    val avgPrice: Int,
    val site: String,
    val category: Category,
    val address: List<Address>,
    val images: List<Image>,
    val phones: List<Phone>,
    val schedule: List<String>,
) {
    private val currentDay = DateTimeUtil.currentDayOfWeek
    private val openTime = this.schedule[currentDay - 1]
        .split("-").first()
        .split(":").first()
        .toInt()
    private val closeTime = this.schedule[currentDay - 1]
        .split("-").last()
        .split(":").first()
        .toInt()
    val isOpen = DateTimeUtil.currentHour in openTime until closeTime
    val time =
        if (isOpen)
            this.schedule[currentDay - 1].split("-").last()
        else
            this.schedule[currentDay - 1].split("-").first()
}

fun List<CafeDomain>.toUi(): List<CafeUi> {
    return map {
        CafeUi(
            id = it.id,
            title = it.title,
            description = it.description,
            avgPrice = it.avgPrice,
            site = it.site,
            category = it.category,
            addresses = it.address,
            images = it.images,
            phones = it.phones,
            schedule = it.schedule,
            isOpen = it.isOpen,
            time = it.time
        )
    }
}