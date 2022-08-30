package hu.gyuriczaadam.sprintformteszt.domain.model

data class TransactionListModel(
    val category: String,
    val currency: String,
    val id: String,
    val paid: String,
    val sum: Int,
    val summary: String
)
