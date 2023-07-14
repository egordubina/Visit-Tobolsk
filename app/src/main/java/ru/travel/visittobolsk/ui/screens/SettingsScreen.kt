package ru.travel.visittobolsk.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Contacts
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.travel.visittobolsk.R
import ru.travel.visittobolsk.ui.navigation.VisitTobolskDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBackButtonClick: () -> Unit, navController: NavController) {
    val settings = listOf(
        SettingsItem(
            title = R.string.contact_us,
            icon = Icons.Rounded.Contacts
        )
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            settings.forEach {
                SettingsRow(title = it.title, icon = it.icon, action = { navController.navigate(VisitTobolskDestination.CONTACTS.name) })
            }
        }
    }
}

@Composable
private fun SettingsRow(@StringRes title: Int, icon: ImageVector, action: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .clickable { action() }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = stringResource(id = title))
        }
        Icon(imageVector = Icons.Rounded.ArrowForwardIos, contentDescription = null)
    }
}

private data class SettingsItem(
    @StringRes val title: Int,
    val icon: ImageVector
)