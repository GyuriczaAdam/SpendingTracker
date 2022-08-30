package hu.gyuriczaadam.sprintformteszt.domain.repositories

import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDto

interface TransactionRepository {
    suspend fun getTransactionsFromApi():TransactionDto
}