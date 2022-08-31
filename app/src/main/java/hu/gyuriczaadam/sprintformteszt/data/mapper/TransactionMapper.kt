package hu.gyuriczaadam.sprintformteszt.data.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDtoItem
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.util.Constants

fun TransactionListEntity.toTransacrtionItem(): TransactionItem {
    return TransactionItem(
        summary = summary,
        paid = paid,
        sum = sum,
        currency = currency,
        id = id!!,
        type = category,
        imageVector = switchImage(category)
    )
}
fun switchImage(category:String):ImageVector{
        when(category){
        Constants.FOOD_TYPE->return Icons.Default.Fastfood
        Constants.TRAVEL_TYPE->return Icons.Default.Tram
        Constants.HOUSING_TYPE->return Icons.Default.House
        Constants.UTILITIES_TYPE->return Icons.Default.PanTool
        Constants.INSURANCE_TYPE->return Icons.Default.HealthAndSafety
        Constants.HEALTHCARE_TYPE->return Icons.Default.Emergency
        Constants.FINANCIAL_TYPE->return Icons.Default.CreditCard
        Constants.LIFESTYLE_TYPE->return Icons.Default.Style
        Constants.ENTERTAINMENT_TYPE->return Icons.Default.MusicVideo
        Constants.MISCELLANEOUS_TYPE->return Icons.Default.MiscellaneousServices
            else->return Icons.Default.DonutLarge
        }
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