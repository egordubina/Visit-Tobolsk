package ru.neuromantics.visittobolsk.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.neuromantics.visittobolsk.ui.viewmodels.ArViewModel
import ru.neuromantics.visittobolsk.ui.viewmodels.CafeDetailScreenViewModel
import ru.neuromantics.visittobolsk.ui.viewmodels.HotelDetailScreenViewModel
import ru.neuromantics.visittobolsk.ui.viewmodels.MostInterestsViewModel
import ru.neuromantics.visittobolsk.ui.viewmodels.MuseumDetailScreenViewModel
import ru.neuromantics.visittobolsk.ui.viewmodels.ParkDetailScreenViewModel

val appModule = module {
    viewModel<MostInterestsViewModel> { MostInterestsViewModel(loadScreenUseCase = get()) }
    viewModel<CafeDetailScreenViewModel> { CafeDetailScreenViewModel(savedStateHandle = get(), loadDetailCafeUseCase = get()) }
    viewModel<MuseumDetailScreenViewModel> { MuseumDetailScreenViewModel(savedStateHandle = get(), loadDetailMuseumUseCase = get()) }
    viewModel<ParkDetailScreenViewModel> { ParkDetailScreenViewModel(savedStateHandle = get(), loadDetailParkUseCase = get()) }
    viewModel<HotelDetailScreenViewModel> { HotelDetailScreenViewModel(savedStateHandle = get(), loadDetailHotelUseCase = get()) }
    viewModel<ArViewModel> { ArViewModel(loadArUseCase = get()) }
}