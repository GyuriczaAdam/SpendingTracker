package hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases

import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import hu.gyuriczaadam.sprintformteszt.util.Resource
import hu.gyuriczaadam.sprintformteszt.util.UIText
import kotlinx.coroutines.flow.flow
import okio.IOException
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.data.mapper.toTransactionEntity
import retrofit2.HttpException

class GetTransactionsFromApiUseCase (
    private val repository: TransactionRepository
    ) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading())
            val transactions = repository.getTransactionsFromApi().map {it.toTransactionEntity()}
            val transactionsList= transactions.map {repository.insertOrIgnoreTransaction(it)}
            emit(Resource.Success(transactionsList))
        }catch (e:HttpException){
            emit(Resource.Error(UIText.DynamicString(e.localizedMessage)))
        }catch (e:IOException){
            emit(Resource.Error(UIText.StringResource(R.string.network_error_text)))
        }
    }

}