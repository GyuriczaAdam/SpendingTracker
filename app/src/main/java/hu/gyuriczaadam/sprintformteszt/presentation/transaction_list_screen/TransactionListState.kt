package hu.gyuriczaadam.sprintformteszt.presentation.transaction_list_screen

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.util.OrderType
import hu.gyuriczaadam.sprintformteszt.util.TransactionOrder

data class TransactionListState(
    val isLoading:Boolean = false,
    val transaction:List<TransactionItem> = emptyList(),
    val error:String ="",
    val transactionOrder: TransactionOrder = TransactionOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
