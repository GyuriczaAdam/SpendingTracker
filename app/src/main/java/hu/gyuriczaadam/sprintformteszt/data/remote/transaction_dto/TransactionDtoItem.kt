package hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto

data class TransactionDtoItem(
    val category: String,
    val currency: String,
    val id: String,
    val paid: String,
    val sum: Long,
    val summary: String
)

