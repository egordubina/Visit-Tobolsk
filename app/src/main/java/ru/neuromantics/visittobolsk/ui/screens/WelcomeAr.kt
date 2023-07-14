//package ru.neuromantics.visittobolsk.ui.screens
//
//import android.Manifest
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.ArrowBackIosNew
//import androidx.compose.material.icons.rounded.Info
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.isGranted
//import com.google.accompanist.permissions.rememberPermissionState
//import ru.neuromantics.visittobolsk.R
//
//@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
//@Composable
//fun WelcomeAr(onBackButtonClick: () -> Unit) {
//    val cameraPermission =
//        rememberPermissionState(permission = Manifest.permission.CAMERA)
//    var showPermissionDialog by rememberSaveable { mutableStateOf(false) }
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(text = "AR") },
//                navigationIcon = {
//                    IconButton(onClick = onBackButtonClick) {
//                        Icon(
//                            imageVector = Icons.Rounded.ArrowBackIosNew,
//                            contentDescription = null
//                        )
//                    }
//                }
//            )
//        }
//    ) {
//        val context = LocalContext.current
//        Column(
//            verticalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(it)
//        ) {
//            Column(
//                modifier = Modifier.verticalScroll(rememberScrollState())
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ar_welcome),
//                    contentDescription = null,
//                    modifier = Modifier.padding(16.dp)
//                )
//                Text(
//                    text = "Добро пожаловать в виртуальный Тобольск!",
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.headlineSmall,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                )
//                Text(
//                    text = "Погрузись в историю Тобольска и посмотри на базарную площадь 100 лет назад!",
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.bodyLarge,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                )
//                Row(
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.Info,
//                        contentDescription = null,
//                        tint = MaterialTheme.colorScheme.error,
//                    )
//                    Text(
//                        text = "Функция может работать некорректно",
//                        style = MaterialTheme.typography.labelLarge,
//                        textAlign = TextAlign.Center,
//                        color = MaterialTheme.colorScheme.error,
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//            }
//            Button(
//                onClick = {
//                    if (cameraPermission.status.isGranted)
//                        Toast.makeText(context, "to AR", Toast.LENGTH_SHORT).show()
//                    else
//                        showPermissionDialog = true
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Text("В виртуальный Тобольск!")
//            }
//        }
//    }
//    if (showPermissionDialog)
//        AlertDialog(
//            onDismissRequest = { showPermissionDialog = false },
//            confirmButton = {
//                Button(onClick = {
//                    showPermissionDialog = false
//                    cameraPermission.launchPermissionRequest()
//                }) {
//                    Text(text = "Да")
//                }
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = { showPermissionDialog = false }) {
//                    Text(text = "Нет")
//                }
//            },
//            title = { Text(text = "Использовать камеру?") },
//            text = { Text(text = "Для работы дополненной реальности необходимо использовать камеру вашего телефона") }
//        )
//}