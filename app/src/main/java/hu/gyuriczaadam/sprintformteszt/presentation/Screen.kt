package hu.gyuriczaadam.sprintformteszt.presentation

sealed class Screen(val route:String) {
    object TransactionListScreen:Screen("transaction_list_screen")
}