package hu.gyuriczaadam.sprintformteszt.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
