package hu.gyuriczaadam.sprintformteszt.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import hu.gyuriczaadam.sprintformteszt.R
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.StackedBarChart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.AddEditTransactionScreen
import hu.gyuriczaadam.sprintformteszt.presentation.common.*
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_charts_screen.TransactionChartScreen
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components.TransactionListScreen
import hu.gyuriczaadam.sprintformteszt.presentation.ui.theme.SprintFormTesztTheme
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                window.navigationBarDividerColor=
                    MaterialTheme.colors.onBackground.copy(alpha = 0.1f).toArgb()
            }

            SprintFormTesztTheme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    title = stringResource(id = R.string.home_text),
                                    route = Screen.TransactionListScreen.route,
                                    icon = Icons.Outlined.Home
                                ),
                                MenuItem(

                                    title = stringResource(id = R.string.stats_text),
                                    route = Screen.TransactionStatisticsScreen.route,
                                    icon = Icons.Outlined.StackedBarChart
                                ),
                            ),
                            onItemClick = {
                                navController.navigate(it.route)
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    }
                ) {
                    NavHost(navController = navController,scaffoldState)
                }
            }
        }
    }

    @Composable
    fun NavHost(navController: NavHostController,scaffoldState: ScaffoldState) {
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
                AddEditTransactionScreen(navController = navController,scaffoldState)
            }
            composable(route = Screen.TransactionStatisticsScreen.route){
                TransactionChartScreen(navController = navController)
            }
        }

    }
}

