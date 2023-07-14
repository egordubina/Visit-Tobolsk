package ru.neuromantics.visittobolsk.ui.uistates

import ru.neuromantics.visittobolsk.domain.models.HotelDomain
import ru.neuromantics.visittobolsk.domain.models.ParkDomain
import ru.neuromantics.visittobolsk.ui.models.CafeUi
import ru.neuromantics.visittobolsk.ui.models.MuseumUi

sealed class MostInterestingUiState {
    object Error : MostInterestingUiState()
    object Loading : MostInterestingUiState()
    data class Content(
        val cafesList: List<CafeUi>,
        val museumsList: List<MuseumUi>,
        val parksList: List<ParkDomain>,
        val hotelsList: List<HotelDomain>,
    ) : MostInterestingUiState()
}