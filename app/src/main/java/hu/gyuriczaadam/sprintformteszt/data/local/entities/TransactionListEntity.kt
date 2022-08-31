package hu.gyuriczaadam.sprintformteszt.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem

@Entity
data class TransactionListEntity(
    val category: String,
    val currency: String,
    @PrimaryKey
    val id: Int ? = null,
    val paid: String,
    val sum: Long,
    val summary: String
    )


class InvalidTransactionException(message: String): Exception(message)
