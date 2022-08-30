package hu.gyuriczaadam.sprintformteszt.domain.model.remote

import hu.gyuriczaadam.sprintformteszt.domain.model.local.TransactionListEntity

data class TransactionListModel(
    val category: String,
    val currency: String,
    val id: String,
    val paid: String,
    val sum: Int,
    val summary: String
)

fun TransactionListModel.toTransactionListEntity():TransactionListEntity{
    return TransactionListEntity(
        category = category,
        currency= currency,
        id = id,
        paid = paid,
        sum = sum,
        summary = summary
    )
}
