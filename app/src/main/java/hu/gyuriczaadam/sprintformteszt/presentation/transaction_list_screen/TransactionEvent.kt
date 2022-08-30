package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import hu.gyuriczaadam.sprintformteszt.util.TransactionTypes

sealed class TransactionEvent{
    data class Order(val transactionTypes: TransactionTypes): TransactionEvent()
    object ToggleOrderSection: TransactionEvent()
    data class OnQueryChange(val query: String) : TransactionEvent()
    object OnSearch : TransactionEvent()
    object OnRefresh:TransactionEvent()
}
