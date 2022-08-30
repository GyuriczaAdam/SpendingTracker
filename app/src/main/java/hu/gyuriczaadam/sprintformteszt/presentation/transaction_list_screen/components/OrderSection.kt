package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.gyuriczaadam.sprintformteszt.util.OrderType
import hu.gyuriczaadam.sprintformteszt.util.TransactionOrder


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    transactionOrder:  TransactionOrder = TransactionOrder.Date(OrderType.Descending),
    onOrderChange: (TransactionOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = transactionOrder is TransactionOrder.Title,
                onSelect = { onOrderChange(TransactionOrder.Title(transactionOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = transactionOrder is TransactionOrder.Date,
                onSelect = { onOrderChange(TransactionOrder.Date(transactionOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = transactionOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(transactionOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = transactionOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(transactionOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}