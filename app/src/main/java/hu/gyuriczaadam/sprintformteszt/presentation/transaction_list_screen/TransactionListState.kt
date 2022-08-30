package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.util.TransactionTypes

data class TransactionListState(
    val isLoading:Boolean = false,
    val transaction:List<TransactionItem> = emptyList(),
    val error:String ="",
    val transactionTypes: TransactionTypes = TransactionTypes.all,
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isOrderSectionVisible: Boolean = false
)
