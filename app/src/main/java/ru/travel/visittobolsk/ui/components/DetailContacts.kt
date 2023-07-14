package ru.travel.visittobolsk.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Web
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.travel.visittobolsk.data.models.Phone

@Composable
fun DetailContacts(sites: List<String>, phones: List<Phone>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(
            text = "Контакты",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall
        )
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sites.forEach { SiteItem(webUrl = it) }
            phones.forEach { PhoneItem(phone = it) }
        }
    }
}

@Composable
private fun SiteItem(webUrl: String) {
    val context = LocalContext.current
    ElevatedButton(onClick = {
        val webPage = Uri.parse(webUrl)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        context.startActivity(intent)
    }) {
        Icon(imageVector = Icons.Rounded.Web, contentDescription = null)
        Text(text = "Группа VK", modifier = Modifier.padding(start = 4.dp))
    }
}

@Composable
private fun PhoneItem(phone: Phone) {
    val context = LocalContext.current
    ElevatedButton(onClick = {
        val intent = Intent(
            Intent.ACTION_DIAL,
            Uri.parse("tel:" + phone.phone)
        )
        val chooser = Intent.createChooser(intent, "Позвонить")
        context.startActivity(chooser)
    }) {
        Icon(imageVector = Icons.Rounded.Phone, contentDescription = null)
        Text(text = phone.phone, modifier = Modifier.padding(start = 4.dp))
    }
}