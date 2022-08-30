package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.gyuriczaadam.sprintformteszt.util.TransactionTypes


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    transactionType:  TransactionTypes = TransactionTypes.travel,
    onClick:(TransactionTypes)->Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Housing",
                selected = transactionType is TransactionTypes.housing,
                onSelect = { onClick(TransactionTypes.housing) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Food",
                selected = transactionType is TransactionTypes.food,
                onSelect = { onClick(TransactionTypes.food)}
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Utilities",
                selected = transactionType is TransactionTypes.utilities,
                onSelect = { onClick(TransactionTypes.utilities)}
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Travel",
                selected = transactionType is TransactionTypes.travel,
                onSelect = {
                   onClick(TransactionTypes.travel)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Insurance",
                selected = transactionType is TransactionTypes.insurance,
                onSelect = { onClick(TransactionTypes.utilities)}
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Healthcare",
                selected = transactionType is TransactionTypes.healthcare,
                onSelect = { onClick(TransactionTypes.utilities)}
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Financial",
                selected = transactionType is TransactionTypes.financial,
                onSelect = {
                    onClick(TransactionTypes.financial)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Lifestyle",
                selected = transactionType is TransactionTypes.lifestyle,
                onSelect = { onClick(TransactionTypes.lifestyle)}
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Entertainment",
                selected = transactionType is TransactionTypes.enetertainment,
                onSelect = { onClick(TransactionTypes.enetertainment)}
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Miscellaneous",
                selected = transactionType is TransactionTypes.miscellaneous,
                onSelect = { onClick(TransactionTypes.miscellaneous)}
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "All",
                selected = transactionType is TransactionTypes.all,
                onSelect = { onClick(TransactionTypes.all)}
            )
        }
    }
}