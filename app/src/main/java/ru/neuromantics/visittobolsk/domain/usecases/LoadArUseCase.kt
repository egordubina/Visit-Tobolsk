package ru.neuromantics.visittobolsk.domain.usecases

import ru.neuromantics.visittobolsk.data.models.ArModel
import ru.neuromantics.visittobolsk.data.network.SharedApi

class LoadArUseCase(private val api: SharedApi) {
    suspend fun loadModels(): List<ArModel> = api.loadArModels().map {
        ArModel(
            id = it.id,
            image = it.image,
            model = it.model,
            name = it.name
        )
    }
}