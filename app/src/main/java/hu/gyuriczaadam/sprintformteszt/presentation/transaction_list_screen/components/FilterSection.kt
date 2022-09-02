package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.util.Constants
import hu.gyuriczaadam.sprintformteszt.util.UIText

@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    transactionType:  String = Constants.ALL_TYPE,
    onClick:(String)->Unit,
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
                selected = transactionType.equals(Constants.HOUSING_TYPE),
                onSelect = { onClick(Constants.HOUSING_TYPE) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.food_type).asString(context),
                selected = transactionType.equals(Constants.FOOD_TYPE),
                onSelect = { onClick(Constants.FOOD_TYPE) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.uitlities_type).asString(context),
                selected = transactionType.equals(Constants.UTILITIES_TYPE),
                onSelect = { onClick(Constants.UTILITIES_TYPE) }
            )
        }
        Spacer(modifier = Modifier.height(localSpacing.spaceSmall))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = UIText.StringResource(R.string.travel_type).asString(context),
                selected = transactionType.equals(Constants.TRAVEL_TYPE),
                onSelect = { onClick(Constants.TRAVEL_TYPE) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.insurance_type).asString(context),
                selected = transactionType.equals(Constants.INSURANCE_TYPE),
                onSelect = { onClick(Constants.INSURANCE_TYPE) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.healthcare_type).asString(context),
                selected = transactionType.equals(Constants.HEALTHCARE_TYPE),
                onSelect = { onClick(Constants.HEALTHCARE_TYPE) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = UIText.StringResource(R.string.financial_type).asString(context),
                selected = transactionType.equals(Constants.FINANCIAL_TYPE),
                onSelect = { onClick(Constants.FINANCIAL_TYPE) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.lifestyle_type).asString(context),
                selected = transactionType.equals(Constants.LIFESTYLE_TYPE),
                onSelect = { onClick(Constants.LIFESTYLE_TYPE) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.entertainment_type).asString(context),
                selected = transactionType.equals(Constants.ENTERTAINMENT_TYPE),
                onSelect = { onClick(Constants.ENTERTAINMENT_TYPE) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = UIText.StringResource(R.string.miscellaneous_type).asString(context),
                selected = transactionType.equals(Constants.MISCELLANEOUS_TYPE),
                onSelect = { onClick(Constants.MISCELLANEOUS_TYPE) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.clothing_type).asString(context),
                selected = transactionType.equals(Constants.CLOTHING_TYPE),
                onSelect = { onClick(Constants.CLOTHING_TYPE) }
            )
            Spacer(modifier = Modifier.width(localSpacing.spaceMicro))
            DefaultRadioButton(
                text = UIText.StringResource(R.string.all_type).asString(context),
                selected = transactionType.equals(Constants.ALL_TYPE),
                onSelect = { onClick(Constants.ALL_TYPE) }
            )
        }
    }
}