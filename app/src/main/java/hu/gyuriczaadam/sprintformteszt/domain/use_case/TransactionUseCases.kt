package hu.gyuriczaadam.sprintformteszt.domain.use_case

data class TransactionUseCases (
    val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    val getTransactionByQueryUseCase: GetTransactionByQueryUseCase,
    val getTransactionsFromApiUseCase: GetTransactionsFromApiUseCase,
    val insertTransactionUseCase: InsertTransactionUseCase,
    val transactionTypesListUseCase: TransactionTypesListUseCase
    )
