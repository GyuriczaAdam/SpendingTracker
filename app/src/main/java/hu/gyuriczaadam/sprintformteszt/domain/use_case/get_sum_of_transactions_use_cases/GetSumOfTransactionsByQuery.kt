package hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases

import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository

class GetSumOfTransactionsByQuery(
   private val repository: TransactionRepository
) {
    suspend operator fun invoke(query:String): Long? {
        return  repository.getSumOfTransactionsByQuery(query)
    }
}