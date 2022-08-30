package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val state = viewModel.state.value
    val localSpacing = LocalSpacing.current
    val scaffoldState = rememberScaffoldState()
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
                //TODO:STRING RESOURCE
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        scaffoldState = scaffoldState
    ) {
    Column(  modifier = Modifier
        .fillMaxSize()
        .padding(18.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.app_title_text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.primary
            )
            IconButton(
                onClick = {
                    viewModel.onEvent(TransactionEvent.ToggleOrderSection)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Sort,
                    //TODO:STRING RESOURCE
                    contentDescription = stringResource(R.string.sort_content_dec)
                )
            }

        }
        AnimatedVisibility(
            visible = state.isOrderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            OrderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                transactionType = state.transactionTypes,
                onClick = {
                    viewModel.onEvent(TransactionEvent.Order(it))
                }
            )
        }
        Spacer(modifier = Modifier.height(localSpacing.spaceMedium))
        SearchTextField(
            text = state.query,
            hint = stringResource(R.string.transaction_search_text),
            onValueChange = {
                viewModel.onEvent(TransactionEvent.OnQueryChange(it))
            },
            shouldShowHint = state.isHintVisible,
            onSearch = {
                viewModel.onEvent(TransactionEvent.OnSearch)
            }
        )

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
                        ) }, modifier = Modifier.fillMaxWidth())
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
            }
        }
    }
}