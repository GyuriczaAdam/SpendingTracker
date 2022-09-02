package hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllTransactionsUseCase (
    private val repository: TransactionRepository
    ) {
    operator fun invoke(): Flow<List<TransactionItem>> {
            return repository.getAllTransactions().map { transactions->
                transactions.sortedByDescending { it.sum }
            }
        }
}