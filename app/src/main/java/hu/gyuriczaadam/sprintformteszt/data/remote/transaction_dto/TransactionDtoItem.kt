package hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionListModel

data class TransactionDtoItem(
    val category: String,
    val currency: String,
    val id: String,
    val paid: String,
    val sum: Int,
    val summary: String
)

fun TransactionDtoItem.toTransactionListModel():TransactionListModel{
    return TransactionListModel(
        category = category,
        currency= currency,
        id = id,
        paid = paid,
        sum = sum,
        summary = summary
    )
}