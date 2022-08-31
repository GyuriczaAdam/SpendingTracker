package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import hu.gyuriczaadam.sprintformteszt.util.Resource
import hu.gyuriczaadam.sprintformteszt.util.UIText
import hu.gyuriczaadam.sprintformteszt.R

class InsertTransactionUseCase  (
    private val repository: TransactionRepository,
    ) {
    suspend operator fun invoke(transactionListEntity: TransactionListEntity,transactionTypeList: List<String>): Resource<Unit> {
        if(transactionListEntity.summary.isBlank()){
            return Resource.Error(UIText.StringResource(R.string.error_save_transaction))
        }
        if(transactionListEntity.category.isBlank()){
            return Resource.Error(UIText.StringResource(R.string.error_save_transaction))
        }
        if(!transactionTypeList.contains(transactionListEntity.category)){
            return Resource.Error(UIText.StringResource(R.string.error_save_transaction))
        }
        if(transactionListEntity.sum<=0){
            return Resource.Error(UIText.StringResource(R.string.error_save_transaction))
        }
       return Resource.Success(repository.insertTransaction(transactionListEntity))
    }
}