package ru.travel.visittobolsk.domain.usecases

import ru.travel.visittobolsk.data.repository.CafesRepositoryImpl

interface SearchUseCase {
    suspend fun load(query: String): List<String>
}

class SearchUseCaseImpl : SearchUseCase {
    override suspend fun load(query: String): List<String> {
        return listOf("1", "2", "3", "4", "5")
    }
}