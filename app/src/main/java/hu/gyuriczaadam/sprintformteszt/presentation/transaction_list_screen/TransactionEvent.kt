package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import hu.gyuriczaadam.sprintformteszt.util.TransactionOrder

sealed class TransactionEvent{
    data class Order(val transactionOrder: TransactionOrder): TransactionEvent()
    object ToggleOrderSection: TransactionEvent()
}
