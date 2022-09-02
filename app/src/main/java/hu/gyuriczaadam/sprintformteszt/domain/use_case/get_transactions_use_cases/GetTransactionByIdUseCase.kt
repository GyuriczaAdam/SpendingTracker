package hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases

import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository

class GetTransactionByIdUseCase(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(id:Int):TransactionListEntity?{
        return repository.getTransactionById(id)
    }
}