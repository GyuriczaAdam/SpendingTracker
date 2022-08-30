package hu.gyuriczaadam.sprintformteszt.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.gyuriczaadam.sprintformteszt.data.local.daos.TransactionDao
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity


@Database(
    entities = [TransactionListEntity::class],
    version = 3
)
abstract class TransactionsDatabase :RoomDatabase(){
    abstract val transactionDao:TransactionDao

}