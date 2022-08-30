package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components.TransparentHintTextField
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.util.TestTags
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditTransactionScreen(
    navController: NavController,
    viewModel: AddEditTransactionViewModel = hiltViewModel()
) {
    val localSpacing = LocalSpacing.current
    val transactionTitleState = viewModel.transactionTitle.value
    val transactionAmountState = viewModel.transactionAmount.value
    val transactionTypeState = viewModel.transactionType.value
    val scaffoldState = rememberScaffoldState()
    
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditTransactionViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditTransactionViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTransactionEvent.SaveTransaction)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                //TODO:STRING RESOURCE
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(localSpacing.spaceMedium)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(localSpacing.spaceMedium))
                TransparentHintTextField(
                    text = transactionTitleState.text,
                    hint = transactionTitleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditTransactionEvent.EnteredTransactionTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTransactionEvent.ChangeTransactionTitelFocus(it))
                    },
                    isHintVisible = transactionTitleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
            }

                Spacer(modifier = Modifier.height(localSpacing.spaceMedium))
                TransparentHintTextField(
                    text = transactionTypeState.text,
                    hint = transactionTypeState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditTransactionEvent.EnteredTransactionType(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTransactionEvent.ChangeTransactionTypeFocus(it))
                    },
                    isHintVisible = transactionTypeState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(localSpacing.spaceMedium)
        ) {
            Spacer(modifier = Modifier.height(localSpacing.spaceExtraLarge))
            TransparentHintTextField(
                text = transactionAmountState.text,
                hint = transactionAmountState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditTransactionEvent.EnteredAmount(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditTransactionEvent.ChangeAmountFocus(it))
                },
                isHintVisible = transactionAmountState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }

}