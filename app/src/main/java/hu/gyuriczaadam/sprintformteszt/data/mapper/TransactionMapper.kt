package hu.gyuriczaadam.sprintformteszt.data.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDtoItem
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.util.Constants
import hu.gyuriczaadam.sprintformteszt.util.switchColor

fun TransactionListEntity.toTransacrtionItem(): TransactionItem {
    return TransactionItem(
        summary = summary,
        paid = paid,
        sum = sum,
        currency = switchCurrency(currency),
        id = id!!,
        type = category,
        imageVector = switchImage(category),
        imageColor = switchColor(sum)
    )
}


fun TransactionDtoItem.toTransactionEntity(): TransactionListEntity {
    return TransactionListEntity(
        category = category,
        currency= currency,
        id = id.toInt(),
        paid = paid.substring(0,10),
        sum = sum,
        summary = summary
    )
}

fun switchImage(category:String):ImageVector{
    when(category){
        Constants.FOOD_TYPE->return Icons.Outlined.Fastfood
        Constants.TRAVEL_TYPE->return Icons.Outlined.Tram
        Constants.HOUSING_TYPE->return Icons.Outlined.House
        Constants.UTILITIES_TYPE->return Icons.Outlined.PanTool
        Constants.INSURANCE_TYPE->return Icons.Outlined.HealthAndSafety
        Constants.HEALTHCARE_TYPE->return Icons.Outlined.Emergency
        Constants.FINANCIAL_TYPE->return Icons.Outlined.CreditCard
        Constants.LIFESTYLE_TYPE->return Icons.Outlined.Style
        Constants.ENTERTAINMENT_TYPE->return Icons.Outlined.MusicVideo
        Constants.MISCELLANEOUS_TYPE->return Icons.Outlined.MiscellaneousServices
        Constants.CLOTHING_TYPE->return Icons.Outlined.ShoppingBag
        else->return Icons.Outlined.QuestionAnswer
    }
}


fun switchCurrency(currency:String):String{
    if(currency.equals("HUF")){
        return "Ft."
    }else{
        return "Eur."
    }
}