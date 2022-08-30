package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.gyuriczaadam.sprintformteszt.data.local.entities.InvalidTransactionException
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetTransactionByIdUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetTransactionByQueryUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.InsertTransactionUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.TransactionTypesListUseCase

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


//TODO:Make wrapper class for usecases
@HiltViewModel
class AddEditTransactionViewModel @Inject
constructor(
    private val insertTransactionUseCase: InsertTransactionUseCase,
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val transactionTypesListUseCase: TransactionTypesListUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel(){
    private val _transactionTitle = mutableStateOf(TransactionTextFieldState(
        hint = "Enter  transaction title..."
    ))
    val transactionTitle:State<TransactionTextFieldState> = _transactionTitle

    private val _transactionType = mutableStateOf(TransactionTextFieldState(
        hint = "Enter  transaction type..."
    ))
    val transactionType:State<TransactionTextFieldState> = _transactionType

    private val _transactionAmount = mutableStateOf(TransactionTextFieldState(
        text = "0",
        hint = "Enter  transaction amount"
    ))
    val transactionAmount:State<TransactionTextFieldState> = _transactionAmount

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTransactionId:Int? = null
    init {
        savedStateHandle.get<Int>("transactionId")?.let { transactionId ->
            Log.d("ID","ID: $transactionId")
            if(transactionId != -1) {
               viewModelScope.launch {
                   getTransactionByIdUseCase(transactionId)?.also { transaction ->
                       currentTransactionId = transaction.id
                       _transactionTitle.value = transactionTitle.value.copy(
                           text = transaction.summary,
                           isHintVisible = false
                       )
                       _transactionType.value = transactionType.value.copy(
                           text = transaction.category,
                           isHintVisible = false
                       )
                       _transactionAmount.value = transactionAmount.value.copy(
                           text = transaction.sum.toString(),
                           isHintVisible = false
                       )
                   }
               }
            }
        }
    }
    //TODO:REFACTOOOR!!
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = sdf.format(Date())

    fun onEvent(event: AddEditTransactionEvent){
        when(event){
            is AddEditTransactionEvent.ChangeAmountFocus-> {
                _transactionAmount.value = transactionAmount.value.copy(
                    isHintVisible = !event.focusState.isFocused && transactionAmount.value.text.isBlank()
                )
            }
            is AddEditTransactionEvent.ChangeTransactionTitelFocus -> {
                _transactionTitle.value = transactionTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && transactionTitle.value.text.isBlank()
                )
            }
            is AddEditTransactionEvent.ChangeTransactionTypeFocus -> {
                _transactionType.value = transactionType.value.copy(
                    isHintVisible = !event.focusState.isFocused && transactionType.value.text.isBlank()
                )
            }
            is AddEditTransactionEvent.EnteredAmount -> {
                _transactionAmount.value = transactionAmount.value.copy(
                    text = event.value
                )
            }
            is AddEditTransactionEvent.EnteredTransactionTitle -> {
                _transactionTitle.value = transactionTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTransactionEvent.EnteredTransactionType -> {
                _transactionType.value = transactionType.value.copy(
                    text = event.value
                )
            }
            AddEditTransactionEvent.SaveTransaction -> {
                viewModelScope.launch {
                    try {
                        insertTransactionUseCase(
                            transactionListEntity = TransactionListEntity(
                                category = transactionType.value.text,
                                currency = "HUF",
                                id = currentTransactionId,
                                paid = currentDate,
                                sum = transactionAmount.value.text.toInt(),
                                summary = transactionTitle.value.text
                            ),
                            transactionTypesListUseCase()
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    }catch (e:InvalidTransactionException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                //TODO:STRING RESOURCE
                                message = e.message?: "Couldn't save transaction"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}