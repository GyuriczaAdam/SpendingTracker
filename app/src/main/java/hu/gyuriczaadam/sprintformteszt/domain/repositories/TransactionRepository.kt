package hu.gyuriczaadam.sprintformteszt.domain.repositories

import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDto
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.domain.model.local.TransactionListEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun getTransactionsFromApi():TransactionDto
    fun getAllTransactions():Flow<List<TransactionListEntity>>
    suspend fun insertTransaction(transactionListEntity: TransactionListEntity)
}