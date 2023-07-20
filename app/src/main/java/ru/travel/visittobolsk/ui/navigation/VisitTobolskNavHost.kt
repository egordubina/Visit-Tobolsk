package ru.travel.visittobolsk.ui.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.koin.androidx.compose.koinViewModel
import ru.travel.visittobolsk.R
import ru.travel.visittobolsk.ui.screens.ArScreen
import ru.travel.visittobolsk.ui.screens.CafeDetailScreen
import ru.travel.visittobolsk.ui.screens.ContactsScreen
import ru.travel.visittobolsk.ui.screens.HotelDetailScreen
import ru.travel.visittobolsk.ui.screens.MostInteresting
import ru.travel.visittobolsk.ui.screens.MuseumDetailScreen
import ru.travel.visittobolsk.ui.screens.ParkDetailScreen
import ru.travel.visittobolsk.ui.screens.SettingsScreen
import ru.travel.visittobolsk.ui.screens.WelcomeAr
import ru.travel.visittobolsk.ui.viewmodels.ArViewModel
import ru.travel.visittobolsk.ui.viewmodels.MostInterestsViewModel

enum class VisitTobolskDestination {
    MOST_INTERESTING,
    SETTINGS,
    CONTACTS,
    WELCOME_AR,
    AR,
    CAFE_DETAIL,
    MUSEUM_DETAIL,
    PARK_DETAIL,
    HOTEL_DETAIL,
}

@Composable
fun VisitTobolskNavHost(navController: NavHostController, modifier: Modifier) {
    val context = LocalContext.current
    val appName = stringResource(R.string.app_name)
    NavHost(
        navController = navController,
        startDestination = VisitTobolskDestination.MOST_INTERESTING.name,
    ) {
        composable(route = VisitTobolskDestination.MOST_INTERESTING.name) {
            val vm: MostInterestsViewModel = koinViewModel()
            val uiState = vm.newUiState.collectAsState()
            MostInteresting(
                uiState = uiState.value,
                onSettingsButtonClick = { navController.navigate(VisitTobolskDestination.SETTINGS.name) },
                onArButtonClick = { navController.navigate(VisitTobolskDestination.WELCOME_AR.name) },
                onCafeCardClick = { cafe -> navController.navigate("${VisitTobolskDestination.CAFE_DETAIL.name}/${cafe.id}") },
                onMuseumCardClick = { museum -> navController.navigate("${VisitTobolskDestination.MUSEUM_DETAIL.name}/${museum.id}") },
                onParkCardClick = { park -> navController.navigate("${VisitTobolskDestination.PARK_DETAIL.name}/${park.id}") },
                onHotelCardClick = { hotel -> navController.navigate("${VisitTobolskDestination.HOTEL_DETAIL.name}/${hotel.id}") },
                vm = vm,
            )
        }
        composable(route = VisitTobolskDestination.WELCOME_AR.name) {
            WelcomeAr(
                onBackButtonClick = { navController.navigateUp() },
                onToArButtonClick = { navController.navigate(VisitTobolskDestination.AR.name) }
            )
        }
        composable(route = VisitTobolskDestination.AR.name) {
            val vm: ArViewModel = koinViewModel()
            val uiState = vm.uiState.collectAsState()
            ArScreen(
                vm = vm,
                state = uiState.value,
                onBackButtonClick = { navController.navigateUp() }
            )
        }
        composable(route = VisitTobolskDestination.SETTINGS.name) {
            SettingsScreen(
                onBackButtonClick = { navController.navigateUp() },
                navController = navController
            )
        }
        composable(route = VisitTobolskDestination.CONTACTS.name) {
            ContactsScreen(
                onBackButtonClick = { navController.navigateUp() }
            )
        }
        composable(
            route = "${VisitTobolskDestination.CAFE_DETAIL.name}/{cafeId}",
            arguments = listOf(navArgument("cafeId") { type = NavType.IntType })
        ) {
            CafeDetailScreen(
                onBackButtonClick = { navController.navigateUp() },
                onShareButtonClick = { cafe ->
                    val intent = Intent()
                    intent.apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "$cafe в $appName!\n\n Скачивай $appName в RuStore!"
                        )
                        putExtra(Intent.EXTRA_STREAM, Uri.parse(""))
                    }
                    val chooser = Intent.createChooser(
                        intent, "$appName в RuStore!"
                    )
                    context.startActivity(chooser)
                }
            )
        }
        composable(
            route = "${VisitTobolskDestination.MUSEUM_DETAIL.name}/{museumId}",
            arguments = listOf(navArgument("museumId") { type = NavType.IntType })
        ) {
            MuseumDetailScreen(
                onBackButtonClick = { navController.navigateUp() },
                onShareButtonClick = { museum ->
                    val intent = Intent()
                    intent.apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "$museum в $appName!\n\n Скачивай $appName в RuStore!"
                        )
                        putExtra(Intent.EXTRA_STREAM, Uri.parse(""))
                    }
                    val chooser = Intent.createChooser(
                        intent, "$appName в RuStore!"
                    )
                    context.startActivity(chooser)
                }
            )
        }
        composable(
            route = "${VisitTobolskDestination.PARK_DETAIL.name}/{parkId}",
            arguments = listOf(navArgument("parkId") { type = NavType.IntType })
        ) {
            ParkDetailScreen(
                onBackButtonClick = { navController.navigateUp() },
                onShareButtonClick = { museum ->
                    val intent = Intent()
                    intent.apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "$museum в $appName!\n\n Скачивай $appName в RuStore!"
                        )
                        putExtra(Intent.EXTRA_STREAM, Uri.parse(""))
                    }
                    val chooser = Intent.createChooser(
                        intent, "$appName в RuStore!"
                    )
                    context.startActivity(chooser)
                }
            )
        }
        composable(
            route = "${VisitTobolskDestination.HOTEL_DETAIL.name}/{hotelId}",
            arguments = listOf(navArgument("hotelId") { type = NavType.IntType })
        ) {
            HotelDetailScreen(
                onBackButtonClick = { navController.navigateUp() },
                onShareButtonClick = { museum ->
                    val intent = Intent()
                    intent.apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "$museum в $appName!\n\n Скачивай $appName в RuStore!"
                        )
                        putExtra(Intent.EXTRA_STREAM, Uri.parse(""))
                    }
                    val chooser = Intent.createChooser(
                        intent, "$appName в RuStore!"
                    )
                    context.startActivity(chooser)
                }
            )
        }
    }
}