package ru.neuromantics.visittobolsk.data

import ru.neuromantics.visittobolsk.data.models.Address
import ru.neuromantics.visittobolsk.data.models.CafeNetwork
import ru.neuromantics.visittobolsk.data.models.Category
import ru.neuromantics.visittobolsk.data.models.HotelNetwork
import ru.neuromantics.visittobolsk.data.models.Image
import ru.neuromantics.visittobolsk.data.models.MuseumNetwork
import ru.neuromantics.visittobolsk.data.models.ParkNetwork
import ru.neuromantics.visittobolsk.data.models.Phone


class TempData {
    companion object {
        private val categories = listOf(
            Category(
                id = 0,
                name = "Кафе"
            ),
            Category(
                id = 1,
                name = "Ресторан"
            ),
        )
        val cafes = listOf(
            CafeNetwork(
                id = 0,
                title = "Калач, карась и шведские булочки",
                description = "Современное заведение, в самом названии которого уже заключен гастрономический и исторический код города – понятный каждому тоболяку и вызывающий любопытство у гостей древней столицы Сибири.",
                avgPrice = 1500,
                site = "https://vk.com/markilev3",
                category = categories[0],
                addresses = listOf(
                    Address(
                        id = 0,
                        addressName = "8 мкр, 31а, 1 этаж",
                        location = ""
                    )
                ),
                images = listOf(
                    Image(
                        id = 0,
                        image = "https://chef.ru/wp-content/uploads/photo-2021-02-01-14-01-05-768x578.jpg",
                        name = "Интерьер кафе"
                    )
                ),
                phones = listOf(
                    Phone(
                        id = 0,
                        phone = "+7‒992‒309‒22‒22",
                        description = ""
                    )
                ),
                schedule = listOf(
                    "9:00-19:00",
                    "9:00-19:00",
                    "9:00-19:00",
                    "9:00-19:00",
                    "9:00-19:00",
                    "9:00-19:00",
                    "9:00-19:00",
                )
            ),
            CafeNetwork(
                id = 1,
                title = "Марк и Лев",
                description = "Современное заведение, в самом названии которого уже заключен гастрономический и исторический код города – понятный каждому тоболяку и вызывающий любопытство у гостей древней столицы Сибири.",
                avgPrice = 2500,
                site = "https://vk.com/markilev3",
                category = categories[1],
                addresses = listOf(
                    Address(
                        id = 1,
                        addressName = "8 мкр, 31а, 2 этаж",
                        location = ""
                    )
                ),
                images = listOf(
                    Image(
                        id = 1,
                        image = "https://assets.gq.ru/photos/5d9f4a585ebed200087d7491/master/w_1600%2Cc_limit/0.jpg",
                        name = "Интерьер ресторана"
                    ),
                    Image(
                        id = 2,
                        image = "https://chef.ru/wp-content/uploads/mark_set-1024x683-1-768x512.jpg",
                        name = ""
                    ),
                    Image(
                        id = 3,
                        image = "https://cdn.iportal.ru/news/2015/99/preview/5aa3070ab0fff5427529278f5427111c0e459294_1280_853_c.jpg",
                        name = "Вход в ресторан"
                    )
                ),
                phones = listOf(
                    Phone(
                        id = 1,
                        phone = "+7‒982‒945‒77‒77",
                        description = ""
                    )
                ),
                schedule = listOf(
                    "13:00-22:00",
                    "13:00-22:00",
                    "13:00-22:00",
                    "13:00-22:00",
                    "13:00-22:00",
                    "13:00-22:00",
                    "13:00-22:00",
                )
            ),
            CafeNetwork(
                id = 2,
                title = "Дольче Вита",
                description = "Настроение в каждой чашке!",
                avgPrice = 1500,
                site = "https://vk.com/dolcevitaclub",
                category = categories[0],
                addresses = listOf(
                    Address(
                        id = 2,
                        addressName = "4 мкр, 9",
                        location = ""
                    )
                ),
                images = listOf(
                    Image(
                        id = 4,
                        image = "https://media-cdn.tripadvisor.com/media/photo-s/07/ab/9a/55/dolce-vita.jpg",
                        name = "Интерьер ресторана"
                    ),
                    Image(
                        id = 5,
                        image = "https://i0.photo.2gis.com/main/branch/97/13652064651444831/common",
                        name = "Интерьер ресторана"
                    )
                ),
                phones = listOf(
                    Phone(
                        id = 2,
                        phone = "+7-3456-24-05-50",
                        description = ""
                    )
                ),
                schedule = listOf(
                    "10:00-23:00",
                    "10:00-23:00",
                    "10:00-23:00",
                    "10:00-23:00",
                    "10:00-23:00",
                    "10:00-23:00",
                    "10:00-23:00",
                )
            ),
            CafeNetwork(
                id = 3,
                title = "Дворцовая кофейня",
                description = "Настроение в каждой чашке!",
                avgPrice = 1000,
                site = "https://vk.com/dolcevitaclub",
                category = categories[0],
                addresses = listOf(
                    Address(
                        id = 3,
                        addressName = "Кр. площадь, 1, стр. 2",
                        location = ""
                    )
                ),
                images = listOf(
                    Image(
                        id = 6,
                        image = "https://media-cdn.tripadvisor.com/media/photo-s/07/26/d8/c4/caption.jpg",
                        name = "Интерьер кофейни"
                    ),
                    Image(
                        id = 7,
                        image = "https://photos.wikimapia.org/p/00/04/91/01/14_big.jpg",
                        name = "Экстерьер кофейни"
                    )
                ),
                phones = listOf(
                    Phone(
                        id = 3,
                        phone = "+7-3456-22-64-51",
                        description = ""
                    )
                ),
                schedule = listOf(
                    "11:00-23:00",
                    "11:00-23:00",
                    "11:00-23:00",
                    "11:00-23:00",
                    "11:00-23:00",
                    "11:00-23:00",
                    "11:00-23:00",
                )
            )
        )
        val museums = listOf(
            MuseumNetwork(
                id = 0,
                title = "Музей семьи императора Николая II",
                images = listOf(
                    Image(
                        id = 0,
                        image = "https://i7.photo.2gis.com/images/branch/0/30258560074501570_dc1c_656x340.jpg",
                        name = ""
                    )
                ),
                address = Address(
                    id = 0,
                    addressName = "Ул. Мира, 10",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 0,
                        phone = "+7 (3456) 27‒70‒72",
                        description = ""
                    )
                ),
                site = "https://vk.com/tiamz",
                price = 0,
                schedule = listOf(
                    "",
                    "10:00-19:00",
                    "10:00-19:00",
                    "10:00-19:00",
                    "10:00-19:00",
                    "10:00-19:00",
                    "10:00-19:00",
                )
            ),
            MuseumNetwork(
                id = 1,
                title = "Музей Тюремный замок",
                images = listOf(
                    Image(
                        id = 1,
                        image = "https://i9.photo.2gis.com/images/branch/0/30258560068400136_91aa_656x340.jpg",
                        name = ""
                    )
                ),
                address = Address(
                    id = 1,
                    addressName = "Кр. Площадь, 5, стр 8",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 1,
                        phone = "+7 (3456) 22‒27‒76",
                        description = ""
                    )
                ),
                site = "https://vk.com/tiamz",
                price = 0,
                schedule = listOf(
                    "",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                )
            ),
            MuseumNetwork(
                id = 2,
                title = "Музей Дворец наместника",
                images = listOf(
                    Image(
                        id = 2,
                        image = "https://i3.photo.2gis.com/images/branch/97/13651536405577953_51b2_656x340.jpg",
                        name = ""
                    )
                ),
                address = Address(
                    id = 2,
                    addressName = "Кр. Площадь, 1, стр 3",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 2,
                        phone = "+7 (3456) 26‒57‒27",
                        description = ""
                    )
                ),
                site = "https://vk.com/tiamz",
                price = 0,
                schedule = listOf(
                    "",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                )
            ),
            MuseumNetwork(
                id = 3,
                title = "Музей Губернский",
                images = listOf(
                    Image(
                        id = 3,
                        image = "https://i0.photo.2gis.com/images/branch/0/30258560062342569_f36a_656x340.jpg",
                        name = ""
                    )
                ),
                address =
                Address(
                    id = 3,
                    addressName = "Пл. Семена Ремезова, 10",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 3,
                        phone = "+7 (3456) 26‒57‒27",
                        description = ""
                    )
                ),
                site = "https://vk.com/tiamz",
                price = 0,
                schedule = listOf(
                    "",
                    "10:00-19:00",
                    "10:00-19:00",
                    "10:00-19:00",
                    "10:00-19:00",
                    "10:00-19:00",
                    "10:00-19:00",
                )
            ),
            MuseumNetwork(
                id = 4,
                title = "Музей Дом Мастеров",
                images = listOf(
                    Image(
                        id = 4,
                        image = "https://i8.photo.2gis.com/images/branch/97/13651536402536145_d598_656x340.jpg",
                        name = ""
                    )
                ),
                address = Address(
                    id = 4,
                    addressName = "Пл. Семена Ремезова, 10",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 4,
                        phone = "+7 (3456) 26‒57‒27",
                        description = ""
                    )
                ),
                site = "https://vk.com/tiamz",
                price = 0,
                schedule = listOf(
                    "",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                    "10:00-18:00",
                )
            ),
        )
        val parks = listOf(
            ParkNetwork(
                id = 0,
                title = "Сад Ермака",
                address = Address(
                    id = 0,
                    addressName = "Сад Ермака",
                    location = ""
                ),
                images = listOf(
                    Image(
                        id = 0,
                        image = "https://i8.photo.2gis.com/images/geo/0/30258560060524207_3f20_656x340.jpg",
                        name = ""
                    )
                ),
                phones = listOf(

                ),
            ),
            ParkNetwork(
                id = 1,
                title = "Сквер имени Ершова",
                address = Address(
                    id = 1,
                    addressName = "Сквер имени Ершова",
                    location = ""
                ),
                images = listOf(
                    Image(
                        id = 1,
                        image = "https://i8.photo.2gis.com/images/geo/0/30258560075225982_08d1_656x340.jpg",
                        name = ""
                    )
                ),
                phones = listOf(

                )
            ),
            ParkNetwork(
                id = 2,
                title = "Роща Журавского",
                address = Address(
                    id = 2,
                    addressName = "Роща Журавского",
                    location = ""
                ),
                images = listOf(
                    Image(
                        id = 0,
                        image = "https://i4.photo.2gis.com/images/geo/0/30258560060524338_fa2e_656x340.jpg",
                        name = ""
                    )
                ),
                phones = listOf(

                ),
            ),
            ParkNetwork(
                id = 3,
                title = "Тематический парк \"Тобол\"",
                address = Address(
                    id = 3,
                    addressName = "Ул. Ленина, 175",
                    location = ""
                ),
                images = listOf(
                    Image(
                        id = 3,
                        image = "https://i8.photo.2gis.com/images/geo/0/30258560060524207_3f20_656x340.jpg",
                        name = ""
                    )
                ),
                phones = listOf(
                    Phone(
                        id = 3,
                        phone = "8‒800‒444‒61‒60",
                        description = ""
                    )
                )
            ),
        )
        val hotels = listOf(
            HotelNetwork(
                id = 0,
                title = "AZIMUT Отель Тобольск",
                images = listOf(
                    Image(
                        id = 0,
                        image = "https://i6.photo.2gis.com/images/branch/0/30258560075068113_230e_656x340.jpg",
                        name = ""
                    )
                ),
                address = Address(
                    id = 0,
                    addressName = "9 мкр, 1",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 0,
                        phone = "+7 (3456) 26‒27‒01",
                        description = ""
                    )
                ),
                price = 3000,
                site = listOf("https://vk.com/azimuttobolsk"),
                stars = 3,
                description = ""
            ),
            HotelNetwork(
                id = 1,
                title = "Георгиевская",
                images = listOf(
                    Image(
                        id = 1,
                        image = "https://i9.photo.2gis.com/images/branch/0/30258560099225664_640c_656x340.jpg",
                        name = ""
                    )
                ),
                address = Address(
                    id = 1,
                    addressName = "Ул. Ленская, 35",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 1,
                        phone = "+7 (3456) 22‒09‒09",
                        description = ""
                    )
                ),
                price = 3600,
                site = listOf("https://vk.com/georgievskayahotel"),
                stars = 3,
                description = ""
            ),
            HotelNetwork(
                id = 2,
                title = "Миррорс Тобольск",
                images = listOf(
                    Image(
                        id = 2,
                        image = "https://i2.photo.2gis.com/images/branch/0/30258560106027549_529c_656x340.jpg",
                        name = ""
                    )
                ),
                address = Address(
                    id = 2,
                    addressName = "Ул. Малая Сибирская, 12",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 2,
                        phone = "+7 (3456) 22‒66‒06",
                        description = ""
                    )
                ),
                price = 3600,
                site = listOf("https://mirros-hotels.com/tobolsk/"),
                stars = 4,
                description = ""
            ),
            HotelNetwork(
                id = 3,
                title = "Сибирь",
                images = listOf(
                    Image(
                        id = 2,
                        image = "https://i6.photo.2gis.com/images/branch/97/13651536416172718_1406_656x340.jpg",
                        name = ""
                    )
                ),
                address = Address(
                    id = 2,
                    addressName = "Пл. Семена Ремезова, 1",
                    location = ""
                ),
                phone = listOf(
                    Phone(
                        id = 2,
                        phone = "+7 (3456) 22‒09‒01",
                        description = ""
                    )
                ),
                price = 1900,
                site = listOf("https://hotel-siberia.com/"),
                stars = 3,
                description = ""
            ),
        )
    }
}