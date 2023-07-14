package ru.neuromantics.visittobolsk.data.repository

import ru.neuromantics.visittobolsk.data.models.MuseumNetwork
import ru.neuromantics.visittobolsk.data.network.SharedApi

interface MuseumsRepository {
    suspend fun loadMuseumById(id: Int): MuseumNetwork
    suspend fun loadAllMuseums(): List<MuseumNetwork>
}

class MuseumsRepositoryImpl(
    private val api: SharedApi,
) : MuseumsRepository {
    override suspend fun loadMuseumById(id: Int): MuseumNetwork = api.loadMuseumById(id = id)

    override suspend fun loadAllMuseums(): List<MuseumNetwork> = api.loadAllMuseums()
}