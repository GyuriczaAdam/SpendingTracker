package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hu.gyuriczaadam.sprintformteszt.util.TransactionTypes
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.util.UIText

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    transactionType:  TransactionTypes = TransactionTypes.travel,
    onClick:(TransactionTypes)->Unit,
    context: Context
) {
    val localSpacing = LocalSpacing.current
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = UIText.StringResource(R.string.housing_type).asString(context),
                selected = transactionType is TransactionTypes.housing,
                onSelect = { onClick(TransactionTypes.housing) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.food_type).asString(context),
                selected = transactionType is TransactionTypes.food,
                onSelect = { onClick(TransactionTypes.food)}
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.uitlities_type).asString(context),
                selected = transactionType is TransactionTypes.utilities,
                onSelect = { onClick(TransactionTypes.utilities)}
            )
        }
        Spacer(modifier = Modifier.height(localSpacing.spaceSmall))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = UIText.StringResource(R.string.travel_type).asString(context),
                selected = transactionType is TransactionTypes.travel,
                onSelect = {
                   onClick(TransactionTypes.travel)
                }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.insurance_type).asString(context),
                selected = transactionType is TransactionTypes.insurance,
                onSelect = { onClick(TransactionTypes.utilities)}
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.healthcare_type).asString(context),
                selected = transactionType is TransactionTypes.healthcare,
                onSelect = { onClick(TransactionTypes.utilities)}
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = UIText.StringResource(R.string.financial_type).asString(context),
                selected = transactionType is TransactionTypes.financial,
                onSelect = {
                    onClick(TransactionTypes.financial)
                }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.lifestyle_type).asString(context),
                selected = transactionType is TransactionTypes.lifestyle,
                onSelect = { onClick(TransactionTypes.lifestyle)}
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.entertainment_type).asString(context),
                selected = transactionType is TransactionTypes.enetertainment,
                onSelect = { onClick(TransactionTypes.enetertainment)}
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = UIText.StringResource(R.string.miscellaneous_type).asString(context),
                selected = transactionType is TransactionTypes.miscellaneous,
                onSelect = { onClick(TransactionTypes.miscellaneous)}
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.all_type).asString(context),
                selected = transactionType is TransactionTypes.all,
                onSelect = { onClick(TransactionTypes.all)}
            )
        }
    }
}