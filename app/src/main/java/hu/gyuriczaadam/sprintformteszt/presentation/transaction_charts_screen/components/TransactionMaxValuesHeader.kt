package hu.gyuriczaadam.sprintformteszt.presentation.transaction_charts_screen.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import hu.gyuriczaadam.sprintformteszt.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components.UnitDisplay

@Composable
fun TransactionMaxValuesHeader(
    modifier: Modifier = Modifier,
    moneySpentSum:Long,
    maxTransactionSum:Long,
    maxTransactionName:String
) {
    val localSpacing = LocalSpacing.current
    val animatedTransactionCount= animateIntAsState(targetValue = moneySpentSum.toInt())
    val animatedMaxTransactionCount= animateIntAsState(targetValue = maxTransactionSum.toInt())
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = localSpacing.spaceLarge,
                vertical = localSpacing.spaceMedium
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            Column {
            
                Text(text = stringResource(id = R.string.money_spent_string),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
                UnitDisplay(
                    amount = animatedTransactionCount.value.toLong(),
                    unit = stringResource(id = R.string.currency_text),
                    amountColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,
                    unitColor = MaterialTheme.colors.onPrimary,
                )
                Spacer(modifier = modifier.height(localSpacing.spaceMedium))

                Text(text = stringResource(id = R.string.max_transaction_name_text) + maxTransactionName,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
                UnitDisplay(
                    amount = animatedMaxTransactionCount.value.toLong(),
                    unit = stringResource(id = R.string.currency_text),
                    amountColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,
                    unitColor = MaterialTheme.colors.onPrimary,
                )
            }
        }

    }
}