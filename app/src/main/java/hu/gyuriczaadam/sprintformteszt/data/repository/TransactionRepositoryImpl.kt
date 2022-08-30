package hu.gyuriczaadam.sprintformteszt.data.repository

import hu.gyuriczaadam.sprintformteszt.data.local.daos.TransactionDao
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.data.local.entities.toTransacrtionItem
import hu.gyuriczaadam.sprintformteszt.data.remote.TransactionApi
import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDto
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val transactionApi: TransactionApi,
    private val transactionDao: TransactionDao
    ):TransactionRepository {
    override suspend fun getTransactionsFromApi(): TransactionDto {
        return transactionApi.getTransactionList()
    }

    override fun getAllTransactions(): Flow<List<TransactionItem>> {
        return transactionDao.getTransactions().map { transaction->
            transaction.map { it.toTransacrtionItem() }
        }
    }

    override suspend fun insertTransaction(transactionListEntity: TransactionListEntity) {
        transactionDao.insertTransaction(transactionListEntity)
    }

    override fun getTransactionsByQuery(query: String): Flow<List<TransactionItem>> {
        return transactionDao.getTransactionsByQuery(query).map { transaction->
            transaction.map { it.toTransacrtionItem() }
        }
    }
}