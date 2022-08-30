package hu.gyuriczaadam.sprintformteszt.data.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDtoItem
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem

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
        "food"->return Icons.Default.FoodBank
        "travel"->return Icons.Default.Tram
        "housing"->return Icons.Default.House
        "utilities"->return Icons.Default.PanTool
        "insurance"->return Icons.Default.HealthAndSafety
        "healthcare"->return Icons.Default.Emergency
        "financial"->return Icons.Default.CreditCard
        "lifestyle"->return Icons.Default.Style
        "entertainment"->return Icons.Default.MusicVideo
        "miscellaneous"->return Icons.Default.MiscellaneousServices
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