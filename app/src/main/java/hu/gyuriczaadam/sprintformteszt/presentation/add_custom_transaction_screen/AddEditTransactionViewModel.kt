package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen

import hu.gyuriczaadam.sprintformteszt.R
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.gyuriczaadam.sprintformteszt.data.local.entities.InvalidTransactionException
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.domain.use_case.TransactionUseCases
import hu.gyuriczaadam.sprintformteszt.util.UIEvent
import hu.gyuriczaadam.sprintformteszt.util.UIText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditTransactionViewModel @Inject
constructor(
    private val transactionUseCases: TransactionUseCases,
    savedStateHandle: SavedStateHandle,

):ViewModel(){
    private val _transactionTitle = mutableStateOf(TransactionTextFieldState(
        hint = UIText.StringResource(R.string.transaction_title_hint)
    ))
    val transactionTitle:State<TransactionTextFieldState> = _transactionTitle

    var error by mutableStateOf<UIText?>(null)
        private set

    var hint by mutableStateOf<UIText?>(null)
        private set
    private val _transactionType = mutableStateOf(TransactionTextFieldState(
        hint = UIText.StringResource(R.string.transaction_type_hint)
    ))
    val transactionType:State<TransactionTextFieldState> = _transactionType
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = sdf.format(Date())
    private val _transactionAmount = mutableStateOf(TransactionTextFieldState(
        text = "1",
        hint = UIText.StringResource(R.string.transaction_type_hint)
    ))
    val transactionAmount:State<TransactionTextFieldState> = _transactionAmount

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTransactionId:Int? = null
    init {
        savedStateHandle.get<Int>("transactionId")?.let { transactionId ->
            if(transactionId != -1) {
               viewModelScope.launch {
                   transactionUseCases.getTransactionByIdUseCase(transactionId)?.also { transaction ->
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
                        transactionUseCases.insertTransactionUseCase(
                            transactionListEntity = TransactionListEntity(
                                category = transactionType.value.text,
                                currency = "HUF",
                                id = currentTransactionId,
                                paid = currentDate,
                                sum = transactionAmount.value.text.toLong(),
                                summary = transactionTitle.value.text
                            ),
                            transactionUseCases.transactionTypesListUseCase()
                        )
                        _eventFlow.emit(UIEvent.SaveTransaction)
                    }catch (e:InvalidTransactionException){
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                //TODO:STRING RESOURCE
                                message = (e.message?: UIText.StringResource(R.string.error_save_transaction)) as UIText
                            )
                        )
                    }
                }
            }
        }
    }


}