package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components.OutlinedTextField
import hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components.TransactionTypeSpinner
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.util.UIEvent
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalComposeUiApi::class)
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
    val context  = LocalContext.current
    val keyBoardController = LocalSoftwareKeyboardController.current


    LaunchedEffect(key1 = keyBoardController){
        viewModel.uiEvent.collect{ event->
            when(event){
                is UIEvent.ShowSnackBar->{
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                    keyBoardController?.hide()
                }
                is UIEvent.NavigateUp-> {
                    navController.navigateUp()
                }
                else ->Unit
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
                Icon(imageVector = Icons.Default.Save, contentDescription = stringResource(R.string.save_costume_transaction))
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .clip(
                    RoundedCornerShape(
                        bottomStart = 50.dp,
                        bottomEnd = 50.dp
                    )
                )
                .background(MaterialTheme.colors.primary)
                .padding(
                    horizontal = localSpacing.spaceMedium,
                    vertical = localSpacing.spaceMedium
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.add_edit_screen_title),
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Column(modifier = Modifier
                .wrapContentSize()
                .padding(localSpacing.spaceLarge)
            ) {
                Spacer(modifier = Modifier.height(localSpacing.spaceMedium))
               OutlinedTextField(
                    text = transactionTitleState.text,
                    hint = transactionTitleState.hint!!,
                    imageVector = Icons.Default.Star,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    onValueChange = {
                        viewModel.onEvent(AddEditTransactionEvent.EnteredTransactionTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTransactionEvent.ChangeTransactionTitelFocus(it))
                        keyBoardController?.hide()
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.body1,
                    context = context
                )

                Spacer(modifier = Modifier.height(localSpacing.spaceMedium))

                TransactionTypeSpinner(options = viewModel.getTransactionTypes() ,
                    onValueChange = {
                    viewModel.onEvent(AddEditTransactionEvent.EnteredTransactionType(it))

                },
                    imageVector = Icons.Default.Checklist,
                    text = transactionTypeState.text
                )

                Spacer(modifier = Modifier.height(localSpacing.spaceMedium))
                OutlinedTextField(
                    text = transactionAmountState.text,
                    hint = transactionAmountState.hint!!,
                    imageVector = Icons.Default.CreditCard,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {
                        viewModel.onEvent(AddEditTransactionEvent.EnteredAmount(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTransactionEvent.ChangeAmountFocus(it))
                    },
                    textStyle = MaterialTheme.typography.body1,
                    singleLine = true,
                    context = context
                )
            }
        }
    }

}