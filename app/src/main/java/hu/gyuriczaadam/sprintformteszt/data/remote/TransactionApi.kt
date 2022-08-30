package hu.gyuriczaadam.sprintformteszt.data.remote

import hu.gyuriczaadam.sprintformteszt.data.remote.transaction_dto.TransactionDto
import retrofit2.http.GET

interface TransactionApi {
    //https://development.sprintform.com/
    @GET("transactions.json")
    suspend fun getTransactionList():TransactionDto
}