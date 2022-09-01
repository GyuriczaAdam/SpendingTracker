package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import kotlinx.coroutines.flow.Flow

class SumOfTransactionsUseCase(
   private val repository: TransactionRepository
) {
    suspend operator fun invoke():Long?{
        return repository.getSumOfTransactions()
    }
}