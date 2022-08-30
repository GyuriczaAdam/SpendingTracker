package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem

data class TransactionListState(
    val isLoading:Boolean = false,
    val transaction:List<TransactionItem> = emptyList(),
    val error:String =""
)
