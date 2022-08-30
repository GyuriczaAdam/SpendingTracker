package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.data.local.entities.InvalidTransactionException
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class InsertTransactionUseCase  @Inject
constructor(
    private val repository: TransactionRepository
    ) {
    //TODO:sTRING RESOURVE
    @Throws(InvalidTransactionException::class)
    suspend operator fun invoke(transactionListEntity: TransactionListEntity){
        if(transactionListEntity.summary.isBlank()){
            throw InvalidTransactionException("The summary of the transaction can't be empty")
        }
        if(transactionListEntity.category.isBlank()){
            throw InvalidTransactionException("The type of the transaction can't be empty")
        }
        if(transactionListEntity.sum<=0){
            throw InvalidTransactionException("The amount of the transaction can't be empty")
        }
        repository.insertTransaction(transactionListEntity)
    }
}