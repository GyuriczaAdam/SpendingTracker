package hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases

import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository

class SumOfTransactionsUseCase(
   private val repository: TransactionRepository
) {
    suspend operator fun invoke():Long?{
        return repository.getSumOfTransactions()
    }
}