package ru.neuromantics.visittobolsk.data.network

import ru.neuromantics.visittobolsk.data.TempData
import ru.neuromantics.visittobolsk.data.models.ArModel
import ru.neuromantics.visittobolsk.data.models.CafeNetwork
import ru.neuromantics.visittobolsk.data.models.HotelNetwork
import ru.neuromantics.visittobolsk.data.models.MuseumNetwork
import ru.neuromantics.visittobolsk.data.models.ParkNetwork

interface SharedApi {
    suspend fun loadAllCafes(): List<CafeNetwork>
    suspend fun loadCafeById(id: Int): CafeNetwork

    suspend fun loadAllMuseums(): List<MuseumNetwork>
    suspend fun loadMuseumById(id: Int): MuseumNetwork

    suspend fun loadAllParks(): List<ParkNetwork>
    suspend fun loadParkById(id: Int): ParkNetwork

    suspend fun loadAllHotels(): List<HotelNetwork>
    suspend fun loadHotelById(id: Int): HotelNetwork

    suspend fun loadArModels(): List<ArModel>
}

//const val BASE_URL = "http://192.168.0.7:8000"

class SharedApiImpl : SharedApi {
//    private val json by lazy { Json { ignoreUnknownKeys = true } }
//    private val client by lazy { HttpClient(CIO)
//        HttpClient {
//            install(ContentNegotiation) {
//                json(
//                    Json {
//                        ignoreUnknownKeys = true
//                        useAlternativeNames = false
//                    }
//                )
//            }
//        }
//    }

    override suspend fun loadAllCafes(): List<CafeNetwork> {
        return try {
//            val response = client.get("$BASE_URL/cafes/cafe_list_short/").bodyAsText()
//            json.decodeFromString<List<CafeNetwork>>(response)
            TempData.cafes
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadCafeById(id: Int): CafeNetwork {
        return try {
            // TODO: Перенести на сервер
            TempData.cafes[id]
//            val response = client.get("$BASE_URL/cafes/$id").bodyAsText()
//            json.decodeFromString<CafeNetwork>(response)
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadAllMuseums(): List<MuseumNetwork> {
        return try {
//            val response = client.get("$BASE_URL/").bodyAsText()
//            json.decodeFromString<List<MuseumNetwork>>(response)
            TempData.museums
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadMuseumById(id: Int): MuseumNetwork {
        return try {
            TempData.museums[id]
//            val response = client.get("$BASE_URL/").bodyAsText()
//            json.decodeFromString<MuseumNetwork>(response)
//            emptyList()
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadAllParks(): List<ParkNetwork> {
        return try {
//            val response = client.get("$BASE_URL/").bodyAsText()
//            json.decodeFromString<List<ParkNetwork>>(response)
            TempData.parks
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadParkById(id: Int): ParkNetwork {
        return try {
//            val response = client.get("$BASE_URL/").bodyAsText()
//            json.decodeFromString<ParkNetwork>(response)
            TempData.parks[id]
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadAllHotels(): List<HotelNetwork> {
        return try {
//            val response = client.get("$BASE_URL/").bodyAsText()
//            json.decodeFromString<List<HotelNetwork>>(response)
            TempData.hotels
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadHotelById(id: Int): HotelNetwork {
        return try {
            TempData.hotels[id]
//            throw RuntimeException()
//            val response = client.get("$BASE_URL/").bodyAsText()
//            json.decodeFromString<HotelNetwork>(response)
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    override suspend fun loadArModels(): List<ArModel> {
        return try {
//            val response = client.get("$BASE_URL/ar/").bodyAsText()
//            json.decodeFromString<List<ArModel>>(response)
            emptyList()
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }
}