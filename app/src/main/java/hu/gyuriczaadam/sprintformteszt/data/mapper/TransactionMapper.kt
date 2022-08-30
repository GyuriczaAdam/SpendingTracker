package hu.gyuriczaadam.sprintformteszt.data.mapper

import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDtoItem
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem

fun TransactionListEntity.toTransacrtionItem(): TransactionItem {
    return TransactionItem(
        summary = summary,
        paid = paid,
        sum = sum,
        currency = currency,
        id = id!!
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