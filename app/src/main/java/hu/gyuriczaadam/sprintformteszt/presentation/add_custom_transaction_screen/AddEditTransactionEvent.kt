package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen

import androidx.compose.ui.focus.FocusState

sealed class AddEditTransactionEvent{
    data class EnteredTransactionTitle(val value: String): AddEditTransactionEvent()
    data class ChangeTransactionTitelFocus(val focusState: FocusState): AddEditTransactionEvent()
    data class EnteredTransactionType(val value: String): AddEditTransactionEvent()
    data class ChangeTransactionTypeFocus(val focusState: FocusState): AddEditTransactionEvent()
    data class EnteredAmount(val value: String): AddEditTransactionEvent()
    data class ChangeAmountFocus(val focusState: FocusState): AddEditTransactionEvent()
    object SaveTransaction: AddEditTransactionEvent()
}
