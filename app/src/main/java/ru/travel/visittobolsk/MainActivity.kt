package ru.travel.visittobolsk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import ru.travel.visittobolsk.ui.theme.VisitTobolskTheme
import ru.travel.visittobolsk.ui.navigation.VisitTobolskNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            VisitTobolskTheme {
                VisitTobolskApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun VisitTobolskApp(modifier: Modifier) {
    val navController = rememberNavController()
    VisitTobolskNavHost(navController = navController, modifier = modifier)
}
