package ru.neuromantics.visittobolsk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import ru.neuromantics.visittobolsk.ui.theme.VisitTobolskTheme
import ru.neuromantics.visittobolsk.ui.navigation.VisitTobolskNavHost

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
