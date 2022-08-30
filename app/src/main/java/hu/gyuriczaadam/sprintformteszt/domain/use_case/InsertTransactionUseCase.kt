package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.data.local.entities.InvalidTransactionException
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class InsertTransactionUseCase  @Inject
constructor(
    private val repository: TransactionRepository,
    ) {
    //TODO:sTRING RESOURVE
    @Throws(InvalidTransactionException::class)
    suspend operator fun invoke(transactionListEntity: TransactionListEntity,transactionTypeList: List<String>){
        if(transactionListEntity.summary.isBlank()){
            throw InvalidTransactionException("The summary of the transaction can't be empty")
        }
        if(transactionListEntity.category.isBlank()){
            throw InvalidTransactionException("There type of the transaction can't be empty")
        }
        if(!transactionTypeList.contains(transactionListEntity.category)){
            throw InvalidTransactionException("There are no transaction type like this")
        }
        if(transactionListEntity.sum<=0){
            throw InvalidTransactionException("The amount of the transaction can't be empty")
        }
        repository.insertTransaction(transactionListEntity)
    }
}