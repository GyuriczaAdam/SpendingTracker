package hu.gyuriczaadam.sprintformteszt.domain.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem

//TODO: Make wrapper package
@Entity
data class TransactionListEntity(
    val category: String,
    val currency: String,
    @PrimaryKey
    val id: String,
    val paid: String,
    val sum: Int,
    val summary: String
    )
fun TransactionListEntity.toTransacrtionItem():TransactionItem{
    return TransactionItem(
        summary = summary,
        paid = paid,
        sum = sum,
        currency = currency
    )
}
