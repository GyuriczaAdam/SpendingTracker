package hu.gyuriczaadam.sprintformteszt.presentation.transaction_charts_screen

import hu.gyuriczaadam.sprintformteszt.domain.model.CategoryAmountModel
import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.util.Constants

data class TransactionChartState(
    val sumOfTransactions:Long = 0,
    val maxTransactionSum:Long = 0,
    val maxTransactionName:String = "",
    val transactionTypeList:List<String> = emptyList(),
    val transactionsSumsByCategory: List<TransactionItem> = emptyList(),
    val transactionsSumValueByCategory: List<CategoryAmountModel> = emptyList(),
    val transactionTypes: String = Constants.ALL_TYPE,
)
