package hu.gyuriczaadam.sprintformteszt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.AddEditTransactionScreen
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components.TransactionListScreen
import hu.gyuriczaadam.sprintformteszt.presentation.ui.theme.SprintFormTesztTheme

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
                    composable(route=Screen.AddEditTransactionScreen.route +"?transactionId={transactionId}",
                        arguments = listOf(
                            navArgument(
                                name = "transactionId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                        ){
                        AddEditTransactionScreen(navController = navController)
                    }
                }
            }
        }
    }
}

