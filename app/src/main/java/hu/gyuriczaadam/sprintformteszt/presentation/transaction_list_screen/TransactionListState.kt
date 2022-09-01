package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.util.Constants

data class TransactionListState(
    val isLoading:Boolean = false,
    val transaction:List<TransactionItem> = emptyList(),
    val error:String ="",
    val transactionTypes: String = Constants.ALL_TYPE,
    val query: String = "",
    val sumOfTransactions:Long = 0,
    val isHintVisible: Boolean = false,
    val isOrderSectionVisible: Boolean = false
)
