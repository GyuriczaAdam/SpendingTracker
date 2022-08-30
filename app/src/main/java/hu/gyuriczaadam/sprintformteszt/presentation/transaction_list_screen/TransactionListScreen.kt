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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
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
                   // navController.navigate(Screen.AddEditNoteScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        scaffoldState = scaffoldState
    ) {
    Column(  modifier = Modifier
        .fillMaxSize()
        .padding(18.dp)) {

        Text(
            text = stringResource(R.string.app_title_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(localSpacing.spaceMedium))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.transaction){transaction->
                TrancationItem(transactionItem = transaction, onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth())
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.transaction.isEmpty() -> {
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