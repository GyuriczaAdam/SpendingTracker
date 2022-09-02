package hu.gyuriczaadam.sprintformteszt.presentation.transaction_charts_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.gyuriczaadam.sprintformteszt.domain.model.CategoryAmountModel
import hu.gyuriczaadam.sprintformteszt.domain.use_case.TransactionUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionChartsViewModel
@Inject constructor(
    private val transactionUseCases: TransactionUseCases
): ViewModel() {
    var state by mutableStateOf(TransactionChartState())
        private set
     var transactionSums:MutableList<CategoryAmountModel> = mutableListOf()
    private var getTransactionsJob: Job? = null
    private var getMaxTransactionsJob: Job? = null
    private var getTransactionsSumsByCategoryJob: Job? = null

    init {
        getAllTransactions()
        getMaxTransaction()
        getTransactionsValueByQuery()

    }

    private fun getAllTransactions(){
        getTransactionsJob?.cancel()
        getTransactionsJob = viewModelScope.launch {
            transactionUseCases.sumOfTransactionsUseCase()?.also {
                state = state.copy(
                    sumOfTransactions = it
                )
            }
        }
    }

    private fun getMaxTransaction(){
        getMaxTransactionsJob?.cancel()
        getMaxTransactionsJob = viewModelScope.launch {
          transactionUseCases.getMaxTransactionUseCase()?.also {
              state=state.copy(
                  maxTransactionSum = it.sum,
                  maxTransactionName = it.summary
              )
            }
        }
    }
    fun getTransactionsValueByQuery(){
        getTransactionsSumsByCategoryJob?.cancel()
        getTransactionsSumsByCategoryJob = viewModelScope.launch {
           transactionUseCases.transactionTypesListUseCase().forEach { query->
              transactionUseCases.getSumOfTransactionsByQuery(query)?.also {
                  if(it > 0){
                      transactionSums.add(CategoryAmountModel(it,query))
                  }
              }
          }
            state  = state.copy(
                transactionsSumValueByCategory = transactionSums.sortedByDescending { it.sumOfCategoryAmount }
            )
        }
    }
}
