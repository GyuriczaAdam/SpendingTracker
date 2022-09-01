package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.TransactionEvent
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.TransactionListViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TransactionOverViewHeader(
    viewModel: TransactionListViewModel,
    context:Context,
    moneySpentSum:Long,
)
{
    val state = viewModel.state
    val localSpacing = LocalSpacing.current
    val keyBoardController = LocalSoftwareKeyboardController.current
    val animatedCalorieCount= animateIntAsState(targetValue = moneySpentSum.toInt())

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
                contentDescription = stringResource(R.string.sort_content_dec)
            )
        }

    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {

        Column {
            Text(text = stringResource(id = R.string.money_spent_string),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary
            )
            UnitDisplay(
                amount = animatedCalorieCount.value.toLong(),
                unit = stringResource(id = R.string.currency_text),
                amountColor = checkAmount(moneySpentSum),
                amountTextSize = 40.sp,
                unitColor = MaterialTheme.colors.primary,
            )
        }
    }
    AnimatedVisibility(
        visible = state.isOrderSectionVisible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        FilterSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            transactionType = state.transactionTypes,
            onClick = {
                viewModel.onEvent(TransactionEvent.Order(it))
            },
            context= context
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
            keyBoardController?.hide()
        }
    )
}

fun checkAmount(amount:Long):Color{
    if(amount<=50000){
        return Color.Green
    }else  if(amount<=100000){
        return Color.Yellow
    }else{
        return Color.Red
    }
}