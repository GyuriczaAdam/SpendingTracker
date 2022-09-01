package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.presentation.Screen
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.TransactionEvent
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.TransactionListViewModel


@Composable
fun TransactionListScreen(
    navController: NavController,
    viewModel: TransactionListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val localSpacing = LocalSpacing.current
    val scaffoldState = rememberScaffoldState()
    val context  = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                   navController.navigate(
                       Screen.AddEditTransactionScreen.route
                   )
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add_button_desc))
            }
        },
        scaffoldState = scaffoldState
    ) {
    Column(  modifier = Modifier
        .fillMaxSize()
        .padding(18.dp)) {

        TransactionOverViewHeader(viewModel = viewModel, context = context, moneySpentSum =state.sumOfTransactions)

        Spacer(modifier = Modifier.height(localSpacing.spaceMedium))

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = false),
            onRefresh = {
            viewModel.onEvent(TransactionEvent.OnRefresh)
        },
            indicator = {state, refreshTrigger ->  
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    scale = true,
                    backgroundColor = MaterialTheme.colors.primary
                )
            }
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.transaction){transaction->
                    TrancationItem(transactionItem = transaction, onClick = {
                        navController.navigate(
                            Screen.AddEditTransactionScreen.route+"?transactionId=${transaction.id}"
                        ) }, modifier = Modifier.fillMaxWidth(),
                        imageVector = transaction.imageVector
                        )
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.transaction.isEmpty()->{
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                }
            }
        }
    }
}