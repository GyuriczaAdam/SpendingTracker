package hu.gyuriczaadam.sprintformteszt.presentation.transaction_charts_screen.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components.TransactionsBar
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components.UnitDisplay

@Composable
fun CategoryChartComponent(
    transactionAmount:Long,
    maxAmount:Long,
    modifier: Modifier = Modifier,
    categoryName:String
) {
    val localSpacing = LocalSpacing.current
    val animatedTransactionValueByCategory= animateIntAsState(targetValue = transactionAmount.toInt())
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(localSpacing.spaceMedium)
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),

        )
        {
            Text(text = stringResource(id = R.string.money_spent_by_category_text) + categoryName,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary
            )
            UnitDisplay(
                amount = animatedTransactionValueByCategory.value.toLong(),
                unit = stringResource(id = R.string.currency_text),
                amountColor = MaterialTheme.colors.primary,
                amountTextSize = 30.sp,
                unitColor = MaterialTheme.colors.primary,
            )
        }
        TransactionsBar(
            transactionAmount = transactionAmount,
            maxAmount = maxAmount,
            modifier = modifier
                .fillMaxWidth()
                .height(30.dp),
        )
    }

}