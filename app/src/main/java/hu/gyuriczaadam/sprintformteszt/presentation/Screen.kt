package hu.gyuriczaadam.sprintformteszt.presentation

sealed class Screen(val route:String) {
    object TransactionListScreen:Screen("transaction_list_screen")
    object AddEditTransactionScreen:Screen("add_edit_transaction_screen")
    object TransactionStatisticsScreen:Screen("transaction_statistics_screen")
}