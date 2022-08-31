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
            return Resource.Error(UIText.StringResource(R.string.invalid_transaction_title_error_text))
        }
        if(transactionListEntity.category.isBlank()){
            return Resource.Error(UIText.StringResource(R.string.empty_transaction_type_error_text))
        }
        if(!transactionTypeList.contains(transactionListEntity.category)){
            return Resource.Error(UIText.StringResource(R.string.invalid_transaction_type_error_text))
        }
        if(transactionListEntity.sum<=0){
            return Resource.Error(UIText.StringResource(R.string.null_transaction_amount_error_text))
        }
       return Resource.Success(repository.insertTransaction(transactionListEntity))
    }
}