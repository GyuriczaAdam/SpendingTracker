package hu.gyuriczaadam.sprintformteszt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components.TransactionListScreen
import hu.gyuriczaadam.sprintformteszt.ui.theme.SprintFormTesztTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SprintFormTesztTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.TransactionListScreen.route
                     ) {
                        composable(route = Screen.TransactionListScreen.route){
                            TransactionListScreen(navController = navController)
                        }
                }
            }
        }
    }
}

