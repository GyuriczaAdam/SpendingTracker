package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.domain.model.local.toTransacrtionItem
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import hu.gyuriczaadam.sprintformteszt.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTransactionsUseCase @Inject constructor(
    private val repository: TransactionRepository
    ) {
    operator fun invoke(): Flow<List<TransactionItem>> {
            return repository.getAllTransactions().map { entity->
                entity.map { it.toTransacrtionItem() }
            }
        }
}