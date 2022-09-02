package hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases

import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository

class GetMaxTransactionUseCase(
   private val repository: TransactionRepository
) {
    operator suspend fun invoke():TransactionListEntity?{
        return repository.getMaxTransaction()
    }
}