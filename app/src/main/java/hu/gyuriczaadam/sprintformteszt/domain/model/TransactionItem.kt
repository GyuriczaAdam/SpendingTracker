package hu.gyuriczaadam.sprintformteszt.domain.model

data class TransactionItem(
    val summary: String,
    val paid: String,
    val sum: Int,
    val currency: String,
    val id:Int
)
