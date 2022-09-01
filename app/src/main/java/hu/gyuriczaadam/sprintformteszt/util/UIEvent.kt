package hu.gyuriczaadam.sprintformteszt.util


sealed class UIEvent{
    object NavigateUp:UIEvent()
    data class ShowSnackBar(val message:UIText) : UIEvent()
    object SaveTransaction:UIEvent()
    object UpdateSumOfTransactions:UIEvent()
}
