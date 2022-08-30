package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen

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
import hu.gyuriczaadam.sprintformteszt.domain.use_case.GetTransactionByQueryUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.InsertTransactionUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject


//TODO:Make wrapper class for usecases
@HiltViewModel
class AddEditTransactionViewModel @Inject
constructor(
    private val insertTransactionUseCase: InsertTransactionUseCase,
    private val getTransactionByQueryUseCase: GetTransactionByQueryUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel(){
    private val _transactionTitle = mutableStateOf(TransactionTextFieldState(
        hint = "Enter  transaction title..."
    ))
    val transactionTitle:State<TransactionTextFieldState> = _transactionTitle

    private val _transactionType = mutableStateOf(TransactionTextFieldState(
        hint = "Enter  transaction title..."
    ))
    val transactionType:State<TransactionTextFieldState> = _transactionType

    private val _transactionAmount = mutableStateOf(TransactionTextFieldState(
        hint = "Enter  transaction title..."
    ))
    val transactionAmount:State<TransactionTextFieldState> = _transactionAmount

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTransactionId:Int? = null
    init {
      /*  savedStateHandle.get<Int>("transactionId")?.let { transactionId ->
            if(transactionId != -1) {
                viewModelScope.launch {
                    getTransactionByQueryUseCase(transactionId.toString())?.also { transaction ->
                        currentTransactionId = transaction.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }*/
    }
    //TODO:REFACTOOOR!!
    val sdf = SimpleDateFormat("dd/M/yyyy")
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
                                id = null,
                                paid = currentDate,
                                sum = transactionAmount.value.text.toInt(),
                                summary = transactionTitle.value.text
                            )
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