package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases.GetAllTransactionsUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases.GetMaxTransactionUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases.GetSumOfTransactionsByQuery
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases.SumOfTransactionsUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases.GetTransactionByIdUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases.GetTransactionByQueryUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases.GetTransactionsFromApiUseCase

data class TransactionUseCases (
    val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    val getTransactionByQueryUseCase: GetTransactionByQueryUseCase,
    val getTransactionsFromApiUseCase: GetTransactionsFromApiUseCase,
    val insertTransactionUseCase: InsertTransactionUseCase,
    val transactionTypesListUseCase: TransactionTypesListUseCase,
    val sumOfTransactionsUseCase: SumOfTransactionsUseCase,
    val getSumOfTransactionsByQuery: GetSumOfTransactionsByQuery,
    val getMaxTransactionUseCase: GetMaxTransactionUseCase,
    )
