package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.domain.use_case.TransactionUseCases
import hu.gyuriczaadam.sprintformteszt.util.Constants
import hu.gyuriczaadam.sprintformteszt.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
):ViewModel() {
    var state by mutableStateOf(TransactionListState())
        private set

    private var getTransactionsJob:Job? = null
    private var getTransactionsByQueryJob:Job? = null

    init {
        getTransactions()
    }

    fun onEvent(event: TransactionEvent){
        when(event){
            is TransactionEvent.Order -> {
                state = TransactionListState(transactionTypes = event.transactionTypes)
                    when(event.transactionTypes){
                        Constants.ALL_TYPE->{
                            getAllTransactions()
                        }
                        Constants.FOOD_TYPE->{
                            getTransactionsByQuery(Constants.FOOD_TYPE)
                        }
                        Constants.HOUSING_TYPE-> {
                            getTransactionsByQuery(Constants.HOUSING_TYPE)
                        }
                        Constants.TRAVEL_TYPE-> {
                            getTransactionsByQuery(Constants.TRAVEL_TYPE)
                        }
                        Constants.ENTERTAINMENT_TYPE-> {
                            getTransactionsByQuery(Constants.ENTERTAINMENT_TYPE)
                        }
                        Constants.FINANCIAL_TYPE-> {
                            getTransactionsByQuery(Constants.FINANCIAL_TYPE)
                        }
                        Constants.HEALTHCARE_TYPE-> {
                            getTransactionsByQuery(Constants.HEALTHCARE_TYPE)
                        }
                        Constants.INSURANCE_TYPE-> {
                            getTransactionsByQuery(Constants.INSURANCE_TYPE)
                        }
                        Constants.LIFESTYLE_TYPE-> {
                            getTransactionsByQuery(Constants.LIFESTYLE_TYPE)
                        }
                        Constants.MISCELLANEOUS_TYPE-> {
                            getTransactionsByQuery(Constants.MISCELLANEOUS_TYPE)
                        }
                        Constants.UTILITIES_TYPE-> {
                            getTransactionsByQuery(Constants.UTILITIES_TYPE)
                        }
                    }
            }
            TransactionEvent.ToggleOrderSection -> {
                state = TransactionListState(isOrderSectionVisible = !state.isOrderSectionVisible , transaction = state.transaction, transactionTypes = state.transactionTypes, sumOfTransactions = state.sumOfTransactions)
            }
            is TransactionEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            TransactionEvent.OnSearch -> {
                getTransactionsByQuery(state.query)
            }
            TransactionEvent.OnRefresh -> {
                getAllTransactions()
            }
        }
    }

    private fun getTransactions(){
    getTransactionsJob?.cancel()
    getTransactionsJob = transactionUseCases.getTransactionsFromApiUseCase().onEach { result->
         when(result){
             is Resource.Error -> {
                 state = state.copy(
                     error = result.message.toString()
                 )
             }
             is Resource.Loading -> {
                 state = state.copy(
                     isLoading = true
                 )
             }
             is Resource.Success -> {
                 state=   state.copy(
                     transaction = getAllTransactions(),
                     isLoading = false
                 )
                }
            }
        }.launchIn(viewModelScope)
    }

  private fun getAllTransactions():List<TransactionItem>{
     getTransactionsJob?.cancel()
     getTransactionsJob = transactionUseCases.getAllTransactionsUseCase().onEach { result->
            state = state.copy(
                transaction = result,
                sumOfTransactions = transactionUseCases.sumOfTransactionsUseCase()!!
            )
        }.launchIn(viewModelScope)
      return state.transaction
    }

    private fun getTransactionsByQuery(query:String){
        getTransactionsByQueryJob?.cancel()
        getTransactionsByQueryJob =  transactionUseCases.getTransactionByQueryUseCase(query).onEach { result->
            state = state.copy(
                transaction = result,
                transactionTypes = query,
                sumOfTransactions = transactionUseCases.getSumOfTransactionsByQuery(query)!!
            )
        }.launchIn(viewModelScope)
    }
}