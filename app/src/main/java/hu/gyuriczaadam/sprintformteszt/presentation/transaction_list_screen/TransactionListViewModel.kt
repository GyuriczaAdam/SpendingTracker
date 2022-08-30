package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetAllTransactionsUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetTransactionByQueryUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetTransactionsFromApiUseCase
import hu.gyuriczaadam.sprintformteszt.util.Resource
import hu.gyuriczaadam.sprintformteszt.util.TransactionTypes
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val getTransactionsFromApiUseCase: GetTransactionsFromApiUseCase,
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val getTransactionByQueryUseCase: GetTransactionByQueryUseCase
):ViewModel() {
    private val _state = mutableStateOf(TransactionListState())
    val state:State<TransactionListState> = _state

    private var getAlTransactionsJob:Job? = null


    init {
        getTransactions()
    }

    fun onEvent(event: TransactionEvent){
        when(event){
            is TransactionEvent.Order -> {
                _state.value = TransactionListState(transactionTypes = event.transactionTypes)
                Log.d("Teszt","Type: ${_state.value.transactionTypes}")
                    when(event.transactionTypes){
                        TransactionTypes.all -> getAllTransactions()
                        TransactionTypes.food -> getTransactionsByQuery("food",event.transactionTypes)
                        TransactionTypes.housing -> getTransactionsByQuery("housing",event.transactionTypes)
                        TransactionTypes.travel -> getTransactionsByQuery("travel",event.transactionTypes)
                        TransactionTypes.enetertainment -> getTransactionsByQuery("entertainment",event.transactionTypes)
                        TransactionTypes.financial -> getTransactionsByQuery("financial",event.transactionTypes)
                        TransactionTypes.healthcare -> getTransactionsByQuery("healthcare",event.transactionTypes)
                        TransactionTypes.insurance -> getTransactionsByQuery("insurance",event.transactionTypes)
                        TransactionTypes.lifestyle -> getTransactionsByQuery("lifestyle",event.transactionTypes)
                        TransactionTypes.miscellaneous -> getTransactionsByQuery("miscellaneous",event.transactionTypes)
                        TransactionTypes.utilities -> getTransactionsByQuery("utilities",event.transactionTypes)
                    }
            }
            TransactionEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is TransactionEvent.OnQueryChange -> {
                _state.value = TransactionListState(query = event.query)
            }
            TransactionEvent.OnSearch -> {
                getTransactionsByQuery(state.value.query,state.value.transactionTypes)
            }
            is TransactionEvent.OnSearchFocusChange -> {
                _state.value = TransactionListState(isHintVisible = !event.isFocused &&state.value.query.isBlank())
            }
            TransactionEvent.OnRefresh -> {
                getAllTransactions()
            }
        }
    }

    private fun getTransactions(){
     val job = getTransactionsFromApiUseCase().onEach { result->
         when(result){
             is Resource.Error -> {
                 _state.value = TransactionListState(error = result.message.toString())
             }
             is Resource.Loading -> {
                 _state.value = TransactionListState(isLoading = true)
             }
             is Resource.Success -> {
                    getAllTransactions()
                }
            }
        }.launchIn(viewModelScope)
        if(job.isCompleted){
            job.cancel()
        }
    }

  private fun getAllTransactions(){
        getAlTransactionsJob?.cancel()
        getAlTransactionsJob = getAllTransactionsUseCase().onEach { result->
            _state.value = TransactionListState(transaction = result )
        }.launchIn(viewModelScope)
    }

    private fun getTransactionsByQuery(query:String,transactionTypes: TransactionTypes){
        getTransactionByQueryUseCase(query).onEach { result->
            _state.value = TransactionListState(transaction = result , transactionTypes =transactionTypes)
        }.launchIn(viewModelScope)
    }
}