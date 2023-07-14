package ru.neuromantics.visittobolsk.di

import org.koin.dsl.module
import ru.neuromantics.visittobolsk.domain.usecases.LoadArUseCase
import ru.neuromantics.visittobolsk.domain.usecases.LoadDetailCafeUseCaseImpl
import ru.neuromantics.visittobolsk.domain.usecases.LoadDetailHotelUseCaseImpl
import ru.neuromantics.visittobolsk.domain.usecases.LoadDetailMuseumUseCaseImpl
import ru.neuromantics.visittobolsk.domain.usecases.LoadDetailParkUseCaseImpl
import ru.neuromantics.visittobolsk.domain.usecases.LoadInterestsScreenUseCaseImpl

val domainModule = module {
    single<LoadInterestsScreenUseCaseImpl> {
        LoadInterestsScreenUseCaseImpl(
            cafesRepository = get(),
            parksRepository = get(),
            museumsRepository = get(),
            hotelsRepository = get()
        )
    }
    single<LoadDetailCafeUseCaseImpl> { LoadDetailCafeUseCaseImpl(cafesRepository = get()) }
    single<LoadDetailMuseumUseCaseImpl> { LoadDetailMuseumUseCaseImpl(museumsRepository = get()) }
    single<LoadDetailParkUseCaseImpl> { LoadDetailParkUseCaseImpl(parkRepository = get()) }
    single<LoadDetailHotelUseCaseImpl> { LoadDetailHotelUseCaseImpl(hotelRepository = get()) }
    single<LoadArUseCase> { LoadArUseCase(api = get()) }
}