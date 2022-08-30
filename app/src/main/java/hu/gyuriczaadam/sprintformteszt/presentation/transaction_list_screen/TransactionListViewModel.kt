package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetTransactionsFromApiUseCase
import hu.gyuriczaadam.sprintformteszt.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val getTransactionsFromApiUseCase: GetTransactionsFromApiUseCase
):ViewModel() {
    private val _state = mutableStateOf(TransactionListState())
    val state:State<TransactionListState> = _state

    init {
        getTransactions()
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
                 _state.value = TransactionListState(transaction = result.data!!)
                }
            }
        }.launchIn(viewModelScope)
        if(job.isCompleted){
            job.cancel()
        }
    }
}