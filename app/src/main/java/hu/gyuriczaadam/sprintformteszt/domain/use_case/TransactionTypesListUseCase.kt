package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import javax.inject.Inject

class TransactionTypesListUseCase @Inject constructor(private val repository: TransactionRepository) {
    operator fun invoke():List<String>{
        val transactionTypes:List<String> = mutableListOf("food","housing","travel","all","utilities","insurance","healthcare","financial","lifestye","entertainment","miscellaneous")
        return transactionTypes
    }
}