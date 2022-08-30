package hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto

import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity

data class TransactionDtoItem(
    val category: String,
    val currency: String,
    val id: String,
    val paid: String,
    val sum: Int,
    val summary: String
)

