package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.domain.model.TransactionItem
import hu.gyuriczaadam.sprintformteszt.domain.model.local.toTransacrtionItem
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import hu.gyuriczaadam.sprintformteszt.util.OrderType
import hu.gyuriczaadam.sprintformteszt.util.Resource
import hu.gyuriczaadam.sprintformteszt.util.TransactionOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTransactionsUseCase @Inject constructor(
    private val repository: TransactionRepository
    ) {
    operator fun invoke(
        transactionOrder: TransactionOrder = TransactionOrder.Date(OrderType.Descending)
    ): Flow<List<TransactionItem>> {
            return repository.getAllTransactions().map { transactions->
                when(transactionOrder.orderType){
                    OrderType.Ascending ->
                    {
                        when(transactionOrder){
                            is TransactionOrder.Date -> transactions.sortedBy { it.paid }
                            is TransactionOrder.Title -> transactions.sortedBy { it.summary.lowercase() }
                        }
                    }
                    OrderType.Descending ->
                    {
                        when(transactionOrder){
                            is TransactionOrder.Date -> transactions.sortedByDescending{ it.paid }
                            is TransactionOrder.Title -> transactions.sortedByDescending { it.summary.lowercase() }
                        }
                    }
                }
            }
        }
}