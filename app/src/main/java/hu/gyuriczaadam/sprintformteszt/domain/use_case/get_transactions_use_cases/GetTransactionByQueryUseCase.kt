package hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionByQueryUseCase(
    private val repository: TransactionRepository
) {
    operator fun invoke(
        query:String
    ): Flow<List<TransactionItem>> {
        return repository.getTransactionsByQuery(query)
    }
}