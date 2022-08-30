package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionListModel

data class TransactionListState(
    val isLoading:Boolean = false,
    val transaction:List<TransactionListModel> = emptyList(),
    val error:String =""
)
