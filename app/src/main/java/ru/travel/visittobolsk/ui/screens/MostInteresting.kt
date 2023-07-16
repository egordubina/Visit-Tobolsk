package ru.travel.visittobolsk.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Attractions
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Hotel
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.travel.visittobolsk.domain.DateTimeUtil
import ru.travel.visittobolsk.domain.models.HotelDomain
import ru.travel.visittobolsk.domain.models.ParkDomain
import ru.travel.visittobolsk.ui.components.ItemCard
import ru.travel.visittobolsk.ui.models.CafeUi
import ru.travel.visittobolsk.ui.models.MuseumUi
import ru.travel.visittobolsk.ui.uistates.MostInterestingUiState
import ru.travel.visittobolsk.ui.viewmodels.MostInterestsViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MostInteresting(
    uiState: MostInterestingUiState,
    onSettingsButtonClick: () -> Unit,
    onArButtonClick: () -> Unit,
    onCafeCardClick: (CafeUi) -> Unit,
    onMuseumCardClick: (MuseumUi) -> Unit,
    onParkCardClick: (ParkDomain) -> Unit,
    onHotelCardClick: (HotelDomain) -> Unit,
    vm: MostInterestsViewModel
) {
//    val uiState = vm.newUiState.collectAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var searchIsActive by rememberSaveable { mutableStateOf(false) }
    val searchBarPadding by animateDpAsState(if (searchIsActive) 0.dp else 16.dp, label = "")
    var isOpenFilterPanel by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val transition = rememberInfiniteTransition(label = "")
    val backgroundColorAnimation by transition.animateColor(
        initialValue = MaterialTheme.colorScheme.tertiaryContainer,
        targetValue = SearchBarDefaults.colors().containerColor,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
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
                onSearch = { vm.search(it) },
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
                        text = when {
                            uiState.isLoading -> "Погружение в Тобольск"
                            uiState.isError -> "Ошибка"
                            searchIsActive -> "Поиск"
                            else -> "Узнай Тобольск"
                        },
                        textAlign = if (searchIsActive) TextAlign.Start else TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = SearchBarDefaults.colors(containerColor = if (uiState.isLoading) backgroundColorAnimation else SearchBarDefaults.colors().containerColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(searchBarPadding)
            ) {
                var imageSize by remember { mutableStateOf(0.dp) }
                val density = LocalDensity.current
                val searchResult = vm.searchResult.collectAsState()
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
                        Text(text = searchResult.value.toString())
                    }
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { isOpenFilterPanel = true }) {
                Icon(imageVector = Icons.Rounded.FilterList, contentDescription = null)
                Text(text = "Help", modifier = Modifier.padding(start = 4.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        ContentScreen(
            isLoading = uiState.isLoading,
            cafesList = uiState.cafesList,
            museumsList = uiState.museumsList,
            parksList = uiState.parksList,
            hotelsList = uiState.hotelsList,
            onCafeCardClick = onCafeCardClick,
            onMuseumCardClick = onMuseumCardClick,
            onParkCardClick = onParkCardClick,
            onHotelCardClick = onHotelCardClick,
            temperature = 14,
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        )
    }
    if (isOpenFilterPanel)
        ModalBottomSheet(onDismissRequest = {
            scope.launch {
                bottomSheetState.hide()
                isOpenFilterPanel = false
            }
        }) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Фильтр",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Тип",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item {
                                FilterChip(
                                    selected = false,
                                    onClick = { /*TODO*/ },
                                    label = {
                                        Text(
                                            text = "Еда",
                                            style = MaterialTheme.typography.labelLarge
                                        )
                                    }
                                )
                            }
                            item {
                                FilterChip(
                                    selected = false,
                                    onClick = { /*TODO*/ },
                                    label = {
                                        Text(
                                            text = "Парки",
                                            style = MaterialTheme.typography.labelLarge
                                        )
                                    }
                                )
                            }
                            item {
                                FilterChip(
                                    selected = false,
                                    onClick = { /*TODO*/ },
                                    label = {
                                        Text(
                                            text = "Музеи",
                                            style = MaterialTheme.typography.labelLarge
                                        )
                                    }
                                )
                            }
                            item {
                                FilterChip(
                                    selected = false,
                                    onClick = { /*TODO*/ },
                                    label = {
                                        Text(
                                            text = "Отели",
                                            style = MaterialTheme.typography.labelLarge
                                        )
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
}


@Composable
private fun ContentScreen(
    isLoading: Boolean,
    cafesList: List<CafeUi>,
    museumsList: List<MuseumUi>,
    parksList: List<ParkDomain>,
    hotelsList: List<HotelDomain>,
    onCafeCardClick: (CafeUi) -> Unit,
    onMuseumCardClick: (MuseumUi) -> Unit,
    onParkCardClick: (ParkDomain) -> Unit,
    onHotelCardClick: (HotelDomain) -> Unit,
    temperature: Int,
    modifier: Modifier
) {
    val time = DateTimeUtil.currentHour
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        item {
            Column {
                Text(
                    text = when (time) {
                        in 5 until 11 -> "Доброе утро"
                        in 11 until 18 -> "Добрый день"
                        in 18 until 23 -> "Добрый вечер"
                        else -> "Доброй ночи"
                    },
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "В Тобольске $temperature°",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
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
//        item {
//            Text(
//                text = "Музеи",
//                style = MaterialTheme.typography.headlineSmall,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp)
//            )
//        }
//        items(museumsList, key = { museum -> museum.title }) { museum ->
//            ItemCard(
//                title = museum.title,
//                images = museum.images,
//                addresses = listOf(museum.address),
//                price = museum.price,
//                isOpen = museum.isOpen,
//                time = ""
//            ) { onMuseumCardClick(museum) }
//        }
    }
}