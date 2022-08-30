package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetAllTransactionsUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetTransactionsFromApiUseCase
import hu.gyuriczaadam.sprintformteszt.util.OrderType
import hu.gyuriczaadam.sprintformteszt.util.Resource
import hu.gyuriczaadam.sprintformteszt.util.TransactionOrder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.job
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val getTransactionsFromApiUseCase: GetTransactionsFromApiUseCase,
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase
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
                if(state.value.transactionOrder::class == event.transactionOrder::class && state.value.transactionOrder.orderType == event.transactionOrder.orderType){
                    return
                }
                getAllTransactions(event.transactionOrder)
            }
            TransactionEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
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
                    getAllTransactions(TransactionOrder.Date(OrderType.Descending))
                }
            }
        }.launchIn(viewModelScope)
        if(job.isCompleted){
            job.cancel()
        }
    }

    private fun getAllTransactions(transactionOrder: TransactionOrder){
        getAlTransactionsJob?.cancel()
        getAlTransactionsJob = getAllTransactionsUseCase(transactionOrder).onEach { result->
            _state.value = TransactionListState(transaction = result , transactionOrder = transactionOrder )
        }.launchIn(viewModelScope)

    }
}