package hu.gyuriczaadam.sprintformteszt.util

sealed class TransactionTypes{
    object housing : TransactionTypes()
    object travel : TransactionTypes()
    object food : TransactionTypes()
    object all:TransactionTypes()
    object utilities:TransactionTypes()
    object insurance:TransactionTypes()
    object healthcare:TransactionTypes()
    object financial:TransactionTypes()
    object lifestyle:TransactionTypes()
    object enetertainment:TransactionTypes()
    object miscellaneous:TransactionTypes()
}

