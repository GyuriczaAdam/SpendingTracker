package hu.gyuriczaadam.sprintformteszt.data.repository

import hu.gyuriczaadam.sprintformteszt.data.remote.TransactionApi
import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDto
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionApi: TransactionApi):TransactionRepository {
    override suspend fun getTransactionsFromApi(): TransactionDto {
        return transactionApi.getTransactionList()
    }
}