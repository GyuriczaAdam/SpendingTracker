package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

sealed class TransactionEvent{
    data class Order(val transactionTypes:String): TransactionEvent()
    object ToggleOrderSection: TransactionEvent()
    data class OnQueryChange(val query: String) : TransactionEvent()
    object OnSearch : TransactionEvent()
    object OnRefresh:TransactionEvent()
}
