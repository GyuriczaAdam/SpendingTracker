package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen

import hu.gyuriczaadam.sprintformteszt.util.UIText

data class TransactionTextFieldState(
    val text: String = "",
    val hint: UIText?,
    val isHintVisible: Boolean = true
)