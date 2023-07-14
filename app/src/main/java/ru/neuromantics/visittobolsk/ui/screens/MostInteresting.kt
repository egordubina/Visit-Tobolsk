package ru.neuromantics.visittobolsk.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Attractions
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Hotel
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.neuromantics.visittobolsk.domain.models.HotelDomain
import ru.neuromantics.visittobolsk.domain.models.ParkDomain
import ru.neuromantics.visittobolsk.ui.components.ItemCard
import ru.neuromantics.visittobolsk.ui.models.CafeUi
import ru.neuromantics.visittobolsk.ui.models.MuseumUi
import ru.neuromantics.visittobolsk.ui.uistates.MostInterestingUiState
import ru.neuromantics.visittobolsk.ui.viewmodels.MostInterestsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostInteresting(
    onSettingsButtonClick: () -> Unit,
    onArButtonClick: () -> Unit,
    onCafeCardClick: (CafeUi) -> Unit,
    onMuseumCardClick: (MuseumUi) -> Unit,
    onParkCardClick: (ParkDomain) -> Unit,
    onHotelCardClick: (HotelDomain) -> Unit,
    vm: MostInterestsViewModel = koinViewModel()
) {
    val uiState = vm.uiState.collectAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var searchIsActive by rememberSaveable { mutableStateOf(false) }
    val searchBarPadding by animateDpAsState(if (searchIsActive) 0.dp else 16.dp, label = "")
    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                active = searchIsActive,
                onActiveChange = {
                    searchIsActive = it
                    if (!it)
                        searchQuery = ""
                },
                onSearch = { },
                leadingIcon = {
//                    if (!searchIsActive)
//                        IconButton(onClick = onArButtonClick) {
//                            Icon(
//                                imageVector = Icons.Rounded.CameraEnhance,
//                                contentDescription = null
//                            )
//                        }
//                    else
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (!searchIsActive)
                        IconButton(onClick = onSettingsButtonClick) {
                            Icon(
                                imageVector = Icons.Rounded.Settings,
                                contentDescription = null
                            )
                        }
                    else
                        IconButton(
                            onClick = {
                                if (searchQuery.isEmpty())
                                    searchIsActive = false
                                else
                                    searchQuery = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = null
                            )
                        }
                },
                placeholder = {
                    Text(
                        text = if (searchIsActive) "Поиск" else "Узнай Тобольск",
                        textAlign = if (searchIsActive) TextAlign.Start else TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(searchBarPadding)
            ) {
                var imageSize by remember { mutableStateOf(0.dp) }
                val density = LocalDensity.current
                AnimatedVisibility(searchIsActive) {
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Surface(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = MaterialTheme.shapes.large,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.33f)
                                    .onGloballyPositioned {
                                        with(density) {
                                            imageSize = it.size.width.toDp()
                                        }
                                    }
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        imageVector = Icons.Rounded.Restaurant,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onTertiaryContainer),
                                        modifier = Modifier.size(imageSize)
                                    )
                                    Text(
                                        text = "Где поесть",
                                        style = MaterialTheme.typography.labelLarge,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                            }
                            Surface(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = MaterialTheme.shapes.large,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.33f)
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        imageVector = Icons.Rounded.Hotel,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onTertiaryContainer),
                                        modifier = Modifier.size(imageSize)
                                    )
                                    Text(
                                        text = "Где отдохнуть",
                                        style = MaterialTheme.typography.labelLarge,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                            }
                            Surface(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = MaterialTheme.shapes.large,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.33f)
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        imageVector = Icons.Rounded.Attractions,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onTertiaryContainer),
                                        modifier = Modifier.size(imageSize)
                                    )
                                    Text(
                                        text = "Куда сходить",
                                        style = MaterialTheme.typography.labelLarge,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                            }
                        }
                        Text(
                            text = "Пока что тут ничего нет...",
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                        Text(
                            text = "Но совсем скоро поиск заработает",
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    ) { padding ->
        AnimatedContent(uiState.value, label = "") { uiState ->
            when (uiState) {
                is MostInterestingUiState.Content -> {
                    ContentScreen(
                        cafesList = uiState.cafesList,
                        museumsList = uiState.museumsList,
                        parksList = uiState.parksList,
                        hotelsList = uiState.hotelsList,
                        onCafeCardClick = onCafeCardClick,
                        onMuseumCardClick = onMuseumCardClick,
                        onParkCardClick = onParkCardClick,
                        onHotelCardClick = onHotelCardClick,
                        modifier = Modifier
                            .padding(padding)
                            .padding(horizontal = 16.dp)
                    )
                }

                MostInterestingUiState.Error -> {
                    ErrorScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    )
                }

                MostInterestingUiState.Loading -> {
                    LoadingScreen(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
private fun ContentScreen(
    cafesList: List<CafeUi>,
    museumsList: List<MuseumUi>,
    parksList: List<ParkDomain>,
    hotelsList: List<HotelDomain>,
    onCafeCardClick: (CafeUi) -> Unit,
    onMuseumCardClick: (MuseumUi) -> Unit,
    onParkCardClick: (ParkDomain) -> Unit,
    onHotelCardClick: (HotelDomain) -> Unit,
    modifier: Modifier
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        item {
            Text(
                text = "Кафе",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        items(cafesList, key = { cafe -> cafe.title }) { cafe ->
            ItemCard(
                title = cafe.title,
                images = cafe.images,
                addresses = cafe.addresses,
                price = cafe.avgPrice,
                isOpen = cafe.isOpen,
                time = cafe.time
            ) { onCafeCardClick(cafe) }
        }
        item {
            Text(
                text = "Отели",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        items(hotelsList, key = { hotel -> hotel.title }) { hotel ->
            ItemCard(
                title = hotel.title,
                images = hotel.images,
                addresses = listOf(hotel.address),
                price = hotel.price,
                isOpen = true,
                time = ""
            ) { onHotelCardClick(hotel) }
        }
        item {
            Text(
                text = "Парки",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        items(parksList, key = { park -> park.title }) { park ->
            ItemCard(
                title = park.title,
                images = park.images,
                addresses = listOf(park.address),
                price = 0,
                isOpen = null,
                time = ""
            ) { onParkCardClick(park) }
        }
        item {
            Text(
                text = "Музеи",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        items(museumsList, key = { museum -> museum.title }) { museum ->
            ItemCard(
                title = museum.title,
                images = museum.images,
                addresses = listOf(museum.address),
                price = museum.price,
                isOpen = museum.isOpen,
                time = museum.time
            ) { onMuseumCardClick(museum) }
        }
        item {
            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun ErrorScreen(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Error",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth()
        )
    }
}