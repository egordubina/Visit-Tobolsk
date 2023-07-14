package ru.travel.visittobolsk.domain.models

import ru.travel.visittobolsk.data.models.Address
import ru.travel.visittobolsk.data.models.Image
import ru.travel.visittobolsk.data.models.Phone
import ru.travel.visittobolsk.domain.DateTimeUtil
import ru.travel.visittobolsk.ui.models.MuseumUi

data class MuseumDomain(
    val id: Int,
    val title: String,
    val images: List<Image>,
    val address: Address,
    val phone: List<Phone>,
    val site: String,
    val price: Int,
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
    private val isOpenInCurrentDay = this.schedule[DateTimeUtil.currentDayOfWeek - 1].isNotEmpty()
    val isOpenInCurrentTime = DateTimeUtil.currentHour in openTime until closeTime
    val time =
        if (isOpenInCurrentTime && isOpenInCurrentDay)
            this.schedule[currentDay - 1].split("-").last()
        else
            this.schedule[currentDay - 1].split("-").first()
}

fun List<MuseumDomain>.toUi(): List<MuseumUi> {
    return map {
        MuseumUi(
            id = it.id,
            title = it.title,
            images = it.images,
            address = it.address,
            phone = it.phone,
            site = it.site,
            price = it.price,
            schedule = it.schedule,
            isOpen = it.isOpenInCurrentTime,
            time = it.time
        )
    }
}