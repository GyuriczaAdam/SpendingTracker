package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetTransactionByQueryUseCase
@Inject constructor(
    private val repository: TransactionRepository
) {
    operator fun invoke(
        query:String
    ): Flow<List<TransactionItem>> {
        return repository.getTransactionsByQuery(query)
    }
}